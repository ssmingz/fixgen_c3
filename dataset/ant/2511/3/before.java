class PlaceHold {
  public void testIllegalName3() {
    expectBuildException("testIllegalName", "Manifest attribute names must not contain '*'");
  }
}
