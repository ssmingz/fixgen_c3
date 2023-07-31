class PlaceHold {
  public void test_removeProgressListenerLorg_eclipse_swt_browser_ProgressListener() {
    try {
      browser.removeProgressListener(null);
      fail("No exception thrown for listener == null");
    } catch (IllegalArgumentException e) {
    }
  }
}
