class PlaceHold {
  public void testDuplicateAttribute() {
    expectBuildException("duplicate.attribute", "the attribute text has already been specified");
  }
}
