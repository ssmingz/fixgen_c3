class PlaceHold {
  public void testIllegalName2() {
    expectBuildException("testIllegalName", "Manifest attribute names must not contain '.'");
  }
}
