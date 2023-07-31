class PlaceHold {
  private static Date _getBuildDate() {
    try {
      return new SimpleDateFormat("yyyyMMdd-HHmm").parse(BUILD_TIME_STRING);
    } catch (Exception e) {
      return null;
    }
  }
}
