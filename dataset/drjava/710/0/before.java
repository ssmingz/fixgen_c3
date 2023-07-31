class PlaceHold {
  private void disableChangeListeners() {
    DrJava.getConfig().removeOptionListener(JUNIT_LOCATION_ENABLED, _junitLocationEnabledListener);
    DrJava.getConfig().removeOptionListener(JUNIT_LOCATION, _junitLocationListener);
    DrJava.getConfig()
        .removeOptionListener(RT_CONCJUNIT_LOCATION_ENABLED, _rtConcJUnitLocationEnabledListener);
    DrJava.getConfig().removeOptionListener(RT_CONCJUNIT_LOCATION, _rtConcJUnitLocationListener);
  }
}
