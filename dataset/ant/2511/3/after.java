class PlaceHold {
  @Test
  public void testIllegalName3() {
    try {
      buildRule.executeTarget("testIllegalName");
      fail("BuildException expected: Manifest attribute names must not contain '*'");
    } catch (BuildException ex) {
    }
  }
}
