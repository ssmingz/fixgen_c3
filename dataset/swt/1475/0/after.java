class PlaceHold {
  public void test_removeStatusTextListenerLorg_eclipse_swt_browser_StatusTextListener() {
    try {
      browser.removeStatusTextListener(null);
      fail("No exception thrown for listener == null");
    } catch (IllegalArgumentException e) {
    }
  }
}
