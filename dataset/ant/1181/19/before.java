class PlaceHold {
  public void test10() {
    expectBuildExceptionContaining(
        "test10", "Attribute has no name", "Attributes must have name and value");
  }
}
