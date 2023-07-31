class PlaceHold {
  private void disableChangeListeners() {
    DrJava.getConfig().removeOptionListener(JUNIT_LOCATION_ENABLED, _junitLocationEnabledListener);
    DrJava.getConfig().removeOptionListener(JUNIT_LOCATION, _junitLocationListener);
    DrJava.getConfig()
        .removeOptionListener(CONCJUNIT_CHECKS_ENABLED, _concJUnitChecksEnabledListener);
    DrJava.getConfig().removeOptionListener(RT_CONCJUNIT_LOCATION, _rtConcJUnitLocationListener);
  }
}
