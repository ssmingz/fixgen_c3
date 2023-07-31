class PlaceHold {
  @Test
  public void testIllegalName2() {
    try {
      buildRule.executeTarget("testIllegalName");
      fail("BuildException expected: Manifest attribute names must not contain '.'");
    } catch (BuildException ex) {
    }
  }
}
