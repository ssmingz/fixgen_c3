class PlaceHold {
  public void testDuplicateElement() {
    expectBuildException("duplicate.element", "the element text has already been specified");
  }
}
