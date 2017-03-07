package cn.sursoft;

/**
 * Created by gtguo on 2/15/2017.
 *     private void forwardAdbSocket(long tcpNum){
 adbCmd.execAdbForward(serial,tcpNum,stfServiceSocket);
 }
 */

import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import java.net.Socket;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClient {
    public static final String TAG = "Sursoft SocketClient ";
    public static final String SocketAddress = "127.0.0.1";
    private static final String stfServiceSocket = "localabstract:stfservice";
    private static final String stfAgentSocket = "localabstract:stfagent";
    private int portId;
    private String devSerial;
    private Socket socket = null;
    private BatteryStatus batteryStatus = null;
    private ConnectivityStatus connectivityStatus = null;
    //private BrowserPackageStatus browserPackageStatus = null;
    //private RotationStatus rotationStatus = null;
    private PhoneStateStatus phoneStateStatus = null;
    private AirplaneModeStatus airplaneModeStatus = null;
    private MessageWriter messageWriter = null;
    /****************
    private DevProperty devProperty = null;
    private DevDoIdentify devDoIdentify = null;
    private DevVision devVision = null;
    private DevDisplay devDisplay = null;
    private DevSdcardStatus devSdcardStatus = null;
    private DevWifiStatus devWifiStatus = null;
     ****************/
    private DevInformationAssembly devInformationAssembly = null;
    private SursoftToolSrrStatus sursoftToolSrrStatus = null;
    //Construct
    public SocketClient(String devSerial){
        java.util.Random random = new java.util.Random();
        this.portId = random.nextInt(65535);
        this.devSerial = devSerial;
        this.batteryStatus = new BatteryStatus(devSerial);
        this.airplaneModeStatus = new AirplaneModeStatus(devSerial);
        this.connectivityStatus = new ConnectivityStatus(devSerial);
        this.phoneStateStatus = new PhoneStateStatus(devSerial);
        this.sursoftToolSrrStatus = new SursoftToolSrrStatus(devSerial);
        System.out.println(TAG+"devSerial:"+devSerial+"portId:"+portId);
    }

    //get private field begin
    public int getPortId(){
        return this.portId;
    }

    public String getDevSerial(){
        return this.devSerial;
    }

    public MessageWriter getMessageWriter(){
        return this.messageWriter;
    }

    public DevInformationAssembly getDevInformationAssembly(){
        return this.devInformationAssembly;
    }
    /****************
    public DevProperty getDevProperty(){
        if(this.devProperty !=null){
            return this.devProperty;
        }else{
            try{
                Thread.sleep(500);
                return this.devProperty;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return  null;
    }

    public DevDoIdentify getDevDoIdentify(){
        if(this.devDoIdentify !=null){
            return this.devDoIdentify;
        }else{
            try{
                Thread.sleep(500);
                return this.devDoIdentify;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return  null;
    }

    public DevVision getDevVision(){
        if(this.devVision !=null){
            return this.devVision;
        }else{
            try{
                Thread.sleep(500);
                return this.devVision;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return  null;
    }

    public DevDisplay getDevDisplay(){
        if(this.devDisplay !=null){
            return this.devDisplay;
        }else{
            try{
                Thread.sleep(500);
                return this.devDisplay;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return  null;
    }

    public DevSdcardStatus getSdcardStatus(){
        if(this.devSdcardStatus !=null){
            return this.devSdcardStatus;
        }else{
            try{
                Thread.sleep(500);
                return this.devSdcardStatus;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return  null;
    }

    public DevWifiStatus getDevWifiStatus(){
        if(this.devWifiStatus !=null){
            return this.devWifiStatus;
        }else{
            try{
                Thread.sleep(500);
                return this.devWifiStatus;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return  null;
    }
     ****************/
    public BatteryStatus getBatteryStatus(){
        return this.batteryStatus;
    }

    public Socket getSocket(){
        return this.socket;
    }
    public ConnectivityStatus getConnectivityStatus(){
        return this.connectivityStatus;
    }

    public PhoneStateStatus getPhoneStateStatus(){
        return this.phoneStateStatus;
    }

    public AirplaneModeStatus getAirplaneModeStatus(){
        return this.airplaneModeStatus;
    }

    public SursoftToolSrrStatus getSursoftToolSrrStatus(){return this.sursoftToolSrrStatus;}
/************
    public RotationStatus getRotationStatus(){
        if(this.rotationStatus !=null){
            return this.rotationStatus;
        }else{
            try{
                Thread.sleep(500);
                return this.rotationStatus;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return  null;
    }
 *************/
    //get private field end

    //Connect socket
    public void socketConnect() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //Socket socketTmp = null;
        try{
            //eg: adb forward tcp:1100 localabstract:stfservice
            new AdbCommand().execAdbForward(this.getDevSerial(),this.getPortId(),stfServiceSocket);
            //eg: new Socket("127.0.0.1",1100)
            this.socket = new Socket(SocketAddress, this.getPortId());
        }catch (IOException e){
           e.printStackTrace();
        }
        createSocket createSc = new createSocket(getSocket());
        executorService.submit(createSc);
        Thread.sleep(500);
    }

    class createSocket extends Thread{
        private Socket socket;
        public createSocket(Socket st){
            this.socket = st;
        }

        @Override
        public void interrupt(){
            try{
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        private void handleEventBattery(Wire.Envelope envelope) throws InvalidProtocolBufferException {
            Wire.BatteryEvent batteryEvent = Wire.BatteryEvent.parseFrom(envelope.getMessage());
            batteryStatus.setBatteryField(batteryEvent.getStatus(),
                    batteryEvent.getHealth(),
                    batteryEvent.getSource(),
                    batteryEvent.getLevel(),
                    batteryEvent.getScale(),
                    batteryEvent.getTemp(),
                    batteryEvent.getVoltage());
        }

        private void handleEventConnect(Wire.Envelope envelope) throws InvalidProtocolBufferException {
            Wire.ConnectivityEvent connectivityEvent = Wire.ConnectivityEvent.parseFrom(envelope.getMessage());
            connectivityStatus.setConnectivityField(connectivityEvent.getConnected(),
                    connectivityEvent.getType(),
                    connectivityEvent.getSubtype(),
                    connectivityEvent.getFailover(),
                    connectivityEvent.getRoaming());
        }

        private void handleEventRotation(Wire.Envelope envelope) throws InvalidProtocolBufferException {
            //Wire.RotationEvent rotationEvent = Wire.RotationEvent.parseFrom(envelope.getMessage());
            //rotationStatus = new RotationStatus(rotationEvent.getRotation());
            System.out.println("Do nothing!");
        }

        private void handleEventBrowserPackage(Wire.Envelope envelope){
            System.out.println("Do nothing!");
        }

        private void handleEventAirplaneMode(Wire.Envelope envelope) throws InvalidProtocolBufferException {
            Wire.AirplaneModeEvent airplaneModeEvent = Wire.AirplaneModeEvent.parseFrom(envelope.getMessage());
            airplaneModeStatus.setAirPlaneModeEnable(airplaneModeEvent.getEnabled());
        }

        private void handleEventPhoneMode(Wire.Envelope envelope) throws InvalidProtocolBufferException {
            Wire.PhoneStateEvent phoneStateEvent = Wire.PhoneStateEvent.parseFrom(envelope.getMessage());
            phoneStateStatus.setPhoneStateStatusField(phoneStateEvent.getState(),
                                        phoneStateEvent.getManual(),
                                        phoneStateEvent.getOperator());
        }

        private void handleGetClipboard(Wire.Envelope envelope) throws InvalidProtocolBufferException {
            Wire.GetClipboardResponse clipboardResponse = Wire.GetClipboardResponse.parseFrom(envelope.getMessage());
            System.out.println(TAG+"Success:"+clipboardResponse.getSuccess());
            System.out.println(TAG+"Success:"+clipboardResponse.getType().getDescriptorForType().toString());
            System.out.println(TAG+"Success:"+clipboardResponse.getText());
        }

        private void handleGetProperties(Wire.Envelope envelope) throws InvalidProtocolBufferException {
            Wire.GetPropertiesResponse propertiesResponse = Wire.GetPropertiesResponse.parseFrom(envelope.getMessage());
            System.out.println(TAG+"Success:"+propertiesResponse.getSuccess());
            for (Wire.Property property : propertiesResponse.getPropertiesList()){
                getDevInformationAssembly().getDevProperty().getDevPropertiesHashMap().put(property.getName(),property.getValue());
            }
        }

        private void handleEventIdentify(Wire.Envelope envelope) throws InvalidProtocolBufferException {
            Wire.DoIdentifyResponse identifyResponse = Wire.DoIdentifyResponse.parseFrom(envelope.getMessage());
            getDevInformationAssembly().getDevDoIdentify().setDevIdentify(identifyResponse.getSuccess());
            getDevInformationAssembly().getDevDoIdentify().setProductModel(identifyResponse.getModel());
            //System.out.println("Success:"+identifyResponse.getSuccess());
        }

        private void handleEventGetVision(Wire.Envelope envelope) throws InvalidProtocolBufferException {

            Wire.GetVersionResponse visionResponse = Wire.GetVersionResponse.parseFrom(envelope.getMessage());
            System.out.println(TAG+"devVision: "+visionResponse.getVersion());
            getDevInformationAssembly().getDevVision().setDevSerial(visionResponse.getVersion());
        }

        private void handleEventGetWifiStatus(Wire.Envelope envelope) throws InvalidProtocolBufferException {

            Wire.GetWifiStatusResponse wifiStatusResponse = Wire.GetWifiStatusResponse.parseFrom(envelope.getMessage());
            System.out.println(TAG+"wifistatus: "+wifiStatusResponse.getStatus());
            getDevInformationAssembly().getDevWifiStatus().setWifiStatus(wifiStatusResponse.getStatus());
        }

        private void handleEventGetSdcardStatus(Wire.Envelope envelope) throws InvalidProtocolBufferException {

            Wire.GetSdStatusResponse sdStatusResponse = Wire.GetSdStatusResponse.parseFrom(envelope.getMessage());
            System.out.println(TAG+"sdcard mounted: "+sdStatusResponse.getMounted());
            getDevInformationAssembly().getDevSdcardStatus().setSdcardStatus(sdStatusResponse.getMounted());
        }

        private void handleEventGetDisplay(Wire.Envelope envelope) throws InvalidProtocolBufferException {
            Wire.GetDisplayResponse displayResponse = Wire.GetDisplayResponse.parseFrom(envelope.getMessage());
            System.out.println(TAG+"Display success: "+displayResponse.getSuccess());
            getDevInformationAssembly().getDevDisplay().setScreenDensity(displayResponse.getDensity());
            getDevInformationAssembly().getDevDisplay().setScreenFps(displayResponse.getFps());
            getDevInformationAssembly().getDevDisplay().setScreenHeight(displayResponse.getHeight());
            getDevInformationAssembly().getDevDisplay().setScreenWidth(displayResponse.getWidth());
            getDevInformationAssembly().getDevDisplay().setScreenXdpi(displayResponse.getXdpi());
            getDevInformationAssembly().getDevDisplay().setScreenYdpi(displayResponse.getYdpi());
            getDevInformationAssembly().getDevDisplay().setScreenRotation(displayResponse.getRotation());
        }

        private void handleEventGetSrrStatus(Wire.Envelope envelope) throws InvalidProtocolBufferException{
            Wire.GetSrrStatusEvent getSrrStatusEvent = Wire.GetSrrStatusEvent.parseFrom(envelope.getMessage());
            System.out.println(TAG+"SRR STATUS:"+getSrrStatusEvent.getStatus());
            getSursoftToolSrrStatus().setSursoftToolSrrStatus(getSrrStatusEvent.getStatus());
        }

        @Override
        public void run(){
            System.out.println(TAG + "Socket running....");
            try{
                messageWriter = new MessageWriter(socket.getOutputStream());
                devInformationAssembly = new DevInformationAssembly(getDevSerial(),getSocket());
                messageWriter.write(devInformationAssembly.getDevDoIdentify().getDoIdentifyEnvelope());
                messageWriter.write(devInformationAssembly.getDevProperty().getPropertiesEnvelope());
                messageWriter.write(devInformationAssembly.getDevVision().getDevVisionEnvelope());
                messageWriter.write(devInformationAssembly.getDevDisplay().getDisplayRequestEnvelope());
                messageWriter.write(devInformationAssembly.getDevSdcardStatus().getSdcardStatusEnvelope());
                messageWriter.write(devInformationAssembly.getDevWifiStatus().getWifiStatusEnvelope());
                /***************
                {
                    devDoIdentify = new DevDoIdentify(getDevSerial(),getSocket());
                    getMessageWriter().write(devDoIdentify.getDoIdentifyEnvelope());
                }
                {
                    devProperty = new DevProperty(getDevSerial(),getSocket());
                    getMessageWriter().write(devProperty.getPropertiesEnvelope());
                }
                {
                    devVision = new DevVision(getDevSerial(),getSocket());
                    getMessageWriter().write(devVision.getDevVisionEnvelope());
                }
                {
                    devDisplay = new DevDisplay(getDevSerial(),getSocket());
                    getMessageWriter().write(devDisplay.getDisplayRequestEnvelope());
                }
                {
                    devSdcardStatus = new DevSdcardStatus(getDevSerial(),getSocket());
                    getMessageWriter().write(devSdcardStatus.getSdcardStatusEnvelope());
                }
                {
                    devWifiStatus = new DevWifiStatus(getDevSerial(),getSocket());
                    getMessageWriter().write(devWifiStatus.getWifiStatusEnvelope());
                }
                 *************/
                while(!isInterrupted()){
                    ////socket read message
                    Wire.Envelope envelope = new MessageReader(socket.getInputStream()).read();
                    if(envelope == null){
                        break;
                    }

                    System.out.println(TAG + envelope.getType());
                    switch (envelope.getType()){
                        case EVENT_BATTERY:
                            handleEventBattery(envelope);
                            break;
                        case EVENT_SRR_STATUS:
                            handleEventGetSrrStatus(envelope);
                            break;
                        case EVENT_CONNECTIVITY:
                            handleEventConnect(envelope);
                            break;
                        case EVENT_ROTATION:
                            handleEventRotation(envelope);
                            break;
                        case EVENT_AIRPLANE_MODE:
                            handleEventAirplaneMode(envelope);
                            break;
                        case EVENT_BROWSER_PACKAGE:
                            handleEventBrowserPackage(envelope);
                            break;
                        case EVENT_PHONE_STATE:
                            handleEventPhoneMode(envelope);
                            break;
                        case DO_IDENTIFY:
                            handleEventIdentify(envelope);
                            break;
                        case GET_CLIPBOARD:
                            handleGetClipboard(envelope);
                            break;
                        case GET_PROPERTIES:
                            handleGetProperties(envelope);
                            break;
                        case GET_VERSION:
                            handleEventGetVision(envelope);
                            break;
                        case GET_DISPLAY:
                            handleEventGetDisplay(envelope);
                            break;
                        case GET_WIFI_STATUS:
                            handleEventGetWifiStatus(envelope);
                            break;
                        case GET_SD_STATUS:
                            handleEventGetSdcardStatus(envelope);
                            break;
                        default:
                            System.out.println(TAG + "Unknowing eventType:" + envelope.getType());
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
