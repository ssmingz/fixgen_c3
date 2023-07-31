class PlaceHold {
  public void testResourceSelectorScenarioSimple() {
    if (getProject().getProperty("ant.home") == null) {
      return;
    }
    BFT bft = new BFT("modifiedselector");
    bft.doTarget("modifiedselectortest-scenario-resourceSimple");
    bft.doTarget("modifiedselectortest-scenario-clean");
    bft.deleteCachefile();
  }
}
