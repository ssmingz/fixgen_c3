class PlaceHold {
  public void test_addLocationListenerLorg_eclipse_swt_browser_LocationListener() {
    try {
      browser.addLocationListener(null);
      fail("No exception thrown for listener == null");
    } catch (IllegalArgumentException e) {
    }
    LocationListener listener =
        new LocationListener() {
          public void changed(LocationEvent event) {}

          public void changing(LocationEvent event) {}
        };
    for (int i = 0; i < 100; i++) {
      browser.addLocationListener(listener);
    }
    for (int i = 0; i < 100; i++) {
      browser.removeLocationListener(listener);
    }
  }
}
