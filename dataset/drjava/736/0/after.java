class PlaceHold {
  private static Date _getBuildDate() {
    try {
      return new SimpleDateFormat("yyyyMMdd-HHmm z").parse(BUILD_TIME_STRING + " GMT");
    } catch (Exception e) {
      return null;
    }
  }
}
