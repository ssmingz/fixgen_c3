class PlaceHold {
  private void enableChangeListeners() {
    DrJava.getConfig().addOptionListener(JUNIT_LOCATION_ENABLED, _junitLocationEnabledListener);
    DrJava.getConfig().addOptionListener(JUNIT_LOCATION, _junitLocationListener);
    DrJava.getConfig()
        .addOptionListener(RT_CONCJUNIT_LOCATION_ENABLED, _rtConcJUnitLocationEnabledListener);
    DrJava.getConfig().addOptionListener(RT_CONCJUNIT_LOCATION, _rtConcJUnitLocationListener);
  }
}
