class PlaceHold {
  public void test11() {
    expectBuildExceptionContaining(
        "test11", "Attribute has no value", "Attributes must have name and value");
  }
}
