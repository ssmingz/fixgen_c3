class PlaceHold {
  public void testDuplicateTextName2() {
    expectBuildException(
        "duplicatetextname2",
        "the attribute name \"text\" has already been used by the text element");
  }
}
