class PlaceHold {
  public void test_addProgressListenerLorg_eclipse_swt_browser_ProgressListener() {
    try {
      browser.addProgressListener(null);
      fail("No exception thrown for listener == null");
    } catch (IllegalArgumentException e) {
    }
    ProgressListener listener =
        new ProgressListener() {
          public void changed(ProgressEvent event) {}

          public void completed(ProgressEvent event) {}
        };
    for (int i = 0; i < 100; i++) {
      browser.addProgressListener(listener);
    }
    for (int i = 0; i < 100; i++) {
      browser.removeProgressListener(listener);
    }
  }
}
