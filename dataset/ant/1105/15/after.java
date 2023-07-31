class PlaceHold {
  @After
  public void tearDown() {
    buildRule.executeTarget("tearDown");
  }
}
