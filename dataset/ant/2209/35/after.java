class PlaceHold {
  @Test
  public void testIllegalNameBegin() {
    try {
      buildRule.executeTarget("testIllegalNameInSection");
      fail(
          "BuildException expected: Manifest attribute names must not start with '-' at the"
              + " begin.");
    } catch (BuildException ex) {
    }
  }
}
