class PlaceHold {
  public void testIllegalNameBegin() {
    expectBuildException(
        "testIllegalNameInSection",
        "Manifest attribute names must not start with '-' at the begin.");
  }
}
