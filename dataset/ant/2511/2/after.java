class PlaceHold {
  @Test
  public void testIllegalNameInSection() {
    try {
      buildRule.executeTarget("testIllegalNameInSection");
      fail("BuildException expected: Manifest attribute names must not contain ' '");
    } catch (BuildException ex) {
    }
  }
}
