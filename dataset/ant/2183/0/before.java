class PlaceHold {
  public void testDoubleAttribute() {
    expectBuildExceptionContaining(
        "doubleAttributeDef",
        "Should have detected duplicate attribute definition",
        "attr1 attribute more than once");
  }
}
