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


v2.0。0 
更新：1.devMap获取方式改为AdbCommand类的静态方法；devMap = AdbCommand.getDevID()能获取电脑上连接的所有手机的devices id,
   2.增加DevInformationAssembly类，手机信息合集，通过此类能获取手机的相关信息,以后信息的添加都在此类中封装。注意：minitor的信息如battery info不在此类中
   3.增加ExecTestFactory类，，此类是执行测试类，构造函数：ExecTestFactory(String userId, String taskName, GregorianCalendar calendar, String serial)
                                    参数介绍：userId：用户ID，taskName：执行task的名称，calendar：task发起的日期时间；serial：指定执行手机的devices id
		方法介绍							
		    public void execTestCaseScript(String devSerial,String scriptPathName,int type);发起执行脚本，Type 测试类型，目前Type=0执行SRR工具，Type=1执行stability测试，其他功能待扩展
			public File getTestReport();获取report路径
			public File getTestLog();获取log路径	
			
	示例代码：SursoftExample.java
