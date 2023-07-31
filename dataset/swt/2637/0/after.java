class PlaceHold {
  public void test_removeCloseWindowListenerLorg_eclipse_swt_browser_CloseWindowListener() {
    try {
      browser.removeCloseWindowListener(null);
      fail("No exception thrown for listener == null");
    } catch (IllegalArgumentException e) {
    }
  }
}
