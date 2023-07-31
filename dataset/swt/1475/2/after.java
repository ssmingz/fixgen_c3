class PlaceHold {
  public void test_removeLocationListenerLorg_eclipse_swt_browser_LocationListener() {
    try {
      browser.removeLocationListener(null);
      fail("No exception thrown for listener == null");
    } catch (IllegalArgumentException e) {
    }
  }
}
