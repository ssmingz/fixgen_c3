class PlaceHold {
  public void
      test_removeVisibilityWindowListenerLorg_eclipse_swt_browser_VisibilityWindowListener() {
    try {
      browser.removeVisibilityWindowListener(null);
      fail("No exception thrown for listener == null");
    } catch (IllegalArgumentException e) {
    }
  }
}
