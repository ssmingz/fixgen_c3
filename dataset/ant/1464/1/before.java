class PlaceHold {
  public void testNestedAB() {
    expectBuildExceptionContaining("nested.ab", "Should have got ambiguous", "ambiguous");
  }
}
