class PlaceHold {
  public void testIllegalName() {
    expectBuildException("testIllegalName", "Manifest attribute names must not contain ' '");
  }
}
