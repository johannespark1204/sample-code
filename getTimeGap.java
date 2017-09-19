
import android.provider.Settings;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.text.NumberFormat;
import java.util.Date;
import java.util.TimeZone;

private static final String TIME_SERVER = "1.kr.pool.ntp.org";

public int getTimeGap() throws IOException {
  int auto_time = Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME, 0);
  int auto_time_zone = Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0);
  long time_gap = 0;

  if (auto_time == 0 || auto_time_zone == 0) {
      NTPUDPClient timeClient = new NTPUDPClient();
      InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
      TimeInfo timeInfo = timeClient.getTime(inetAddress);
      long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();   //server time

      Date time = new Date(returnTime);
      long currentTime = System.currentTimeMillis();
      time_gap = Math.abs((currentTime - returnTime)) / 1000;
  }
  return time_gap;
}

// getTimeGap(); 시간차이 계산
// API 호출
