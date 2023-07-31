class PlaceHold {
  public void test_addProgressListenerLorg_eclipse_swt_browser_ProgressListener() {
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
