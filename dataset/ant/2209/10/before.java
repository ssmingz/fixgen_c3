class PlaceHold {
  public void testDuplicateTextName() {
    expectBuildException("duplicatetextname", "the name \"text\" is already used as an attribute");
  }
}
