1.JAR包目录./test/libs
2.STFService.apk安装路径：./
2.example代码：./src
3.public类说明：

AdbCommand.java :
重要方法
public HashMap getDevID(){} 返回HashMap<String(devSerial),String(status)>，该hashMap记录adb devices的返回值
public void execReboot(String serial)重启对应Serial的手机
其他如execAdbForward，execShellCommand，execUninstallApk，execInstallApk分别对应adb forward,adb shell,adb uninstall,adb install命令

SocketClient.java:
构造器：public SocketClient(String devSerial){}，注意参数一定要是手机的serial，获取方法见上getDevId
重要方法
public void socketConnect() 创建socket与手机socketServer通讯，并监听端口
以下都是返回对象的实例，通过对象提供的public get方法可以获取相应的信息，见MyClass.java 
public DevDoIdentify getDevDoIdentify 与手机握手，并返回手机的MODEL.
public BatteryStatus getBatteryStatus 获取手机的电池信息，包括电池电量电压温度状态等等信息
public DevProperty getDevProperty 获取手机的imei,imsi,phoneNumber,iccid,operator,network等key值信息
public DevVision getDevVision 获取手机的android版本号，sdk版本号，如android：5.1.1，SDK:22
public DevDisplay getDevDisplay获取手机分辨率信息，包括Density，Rotation，Fps，Height，Width，Xdpi，Ydpi
public DevWifiStatus getDevWifiStatus 获取手机wifi状态，是否连接
public DevSdcardStatus getSdcardStatus获取手机SDcard是否mounted

更新：增加了minitor功能，能监听battery，airplane，connect，state，SRR status的状态变化。详见MyClass.java

运行MyClass.java需要确保手机连上adb，并安装了STFService.apk，注意：Myclass.java默认的dev serial是0123456789ABCDEF，需要自行设置

