class PlaceHold {
  public void testResourceSelectorScenarioSimple() {
    BFT bft = new BFT("modifiedselector");
    bft.doTarget("modifiedselectortest-scenario-resourceSimple");
    bft.doTarget("modifiedselectortest-scenario-clean");
    bft.deleteCachefile();
  }
}
