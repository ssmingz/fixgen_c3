class PlaceHold {
  public void test_removeOpenWindowListenerLorg_eclipse_swt_browser_OpenWindowListener() {
    try {
      browser.removeOpenWindowListener(null);
      fail("No exception thrown for listener == null");
    } catch (IllegalArgumentException e) {
    }
  }
}
