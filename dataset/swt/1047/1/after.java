class PlaceHold {
  public void test_addStatusTextListenerLorg_eclipse_swt_browser_StatusTextListener() {
    try {
      browser.addStatusTextListener(null);
      fail("No exception thrown for listener == null");
    } catch (IllegalArgumentException e) {
    }
    StatusTextListener listener =
        new StatusTextListener() {
          public void changed(StatusTextEvent event) {}
        };
    for (int i = 0; i < 100; i++) {
      browser.addStatusTextListener(listener);
    }
    for (int i = 0; i < 100; i++) {
      browser.removeStatusTextListener(listener);
    }
  }
}
