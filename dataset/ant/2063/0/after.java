class PlaceHold {
  public void testIllegalNameInSection() {
    expectBuildException(
        "testIllegalNameInSection", "Manifest attribute names must not contain ' '");
  }
}
