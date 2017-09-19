# sample-code
샘플코드 저장소입니다.
// 라이브러리 
compile ‘commons-net:commons-net:3.3’

// ntp 서버
private static final String TIME_SERVER = “1.kr.pool.ntp.org”;

// 자동 시간 체크
int auto_time = Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME, 0);
int auto_time_zone = Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0);

// ntp 시간
NTPUDPClient timeClient = new NTPUDPClient();
InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
TimeInfo timeInfo = timeClient.getTime(inetAddress);
long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();   //server time
Date time = new Date(returnTime);
