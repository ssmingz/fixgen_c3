class PlaceHold {
  public void testScenarioCustomSelectorSettings() {
    if (getProject().getProperty("ant.home") == null) {
      return;
    }
    doScenarioTest(
        "modifiedselectortest-scenario-customselector-settings", "core.cache.properties");
  }
}
