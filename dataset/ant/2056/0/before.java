class PlaceHold {
  public void test13() {
    expectBuildExceptionContaining(
        "test13",
        "Duplicate Attribute",
        "The attribute \"Test\" may not occur more than once in the same section");
  }
}
