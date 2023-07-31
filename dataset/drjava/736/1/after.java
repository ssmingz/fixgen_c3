class PlaceHold {
  public void testUtilVersion() throws Throwable {
    Date required = new SimpleDateFormat("yyyyMMdd-HHmm z").parse(REQUIRED_UTIL_VERSION + " GMT");
    Date found = Version.BUILD_TIME;
    assertTrue(
        ((("Util package date is " + found) + ", but at least ") + required)
            + " was required! You need to update/compile the util package.",
        !required.after(found));
  }
}
