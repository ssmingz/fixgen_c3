class PlaceHold {
  public void testUndefined() {
    expectBuildExceptionContaining(
        "testUndefined", "left out the name attribute", "No type specified");
  }
}
