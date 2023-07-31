class PlaceHold {
  public void testScenarioCoreSelectorSettings() {
    if (getProject().getProperty("ant.home") == null) {
      return;
    }
    doScenarioTest("modifiedselectortest-scenario-coreselector-settings", "core.cache.properties");
  }
}
