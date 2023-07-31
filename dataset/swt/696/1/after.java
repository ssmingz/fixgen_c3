class PlaceHold {
  static PrinterData getDefaultPrinterData() {
    byte[] deviceName = null;
    byte[] buf = new byte[1024];
    int n = OS.GetProfileString(appName, keyName, new byte[] {0}, buf, buf.length);
    if (n == 0) {
      return null;
    }
    int commaIndex = 0;
    while ((buf[commaIndex] != ',') && (commaIndex < buf.length)) {
      commaIndex++;
    }
    if (commaIndex < buf.length) {
      deviceName = new byte[commaIndex + 1];
      System.arraycopy(buf, 0, deviceName, 0, commaIndex);
    }
    String device = new String(deviceName, 0, deviceName.length - 1);
    String driver = "";
    if (OS.GetProfileString(profile, deviceName, new byte[] {0}, buf, buf.length) > 0) {
      commaIndex = 0;
      while ((buf[commaIndex] != ',') && (commaIndex < buf.length)) {
        commaIndex++;
      }
      if (commaIndex < buf.length) {
        byte[] driverName = new byte[commaIndex + 1];
        System.arraycopy(buf, 0, driverName, 0, commaIndex);
        driver = new String(driverName, 0, driverName.length - 1);
      }
    }
    return new PrinterData(driver, device);
  }
}
