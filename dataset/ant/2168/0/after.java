class PlaceHold {
  public void testScenarioCoreSelectorDefaults() {
    if (getProject().getProperty("ant.home") == null) {
      return;
    }
    doScenarioTest("modifiedselectortest-scenario-coreselector-defaults", "cache.properties");
  }
}
