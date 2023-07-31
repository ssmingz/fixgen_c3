class PlaceHold {
  @Test
  public void testNoPropertyDefined() {
    try {
      buildRule.executeTarget("testNoPropertyDefined");
      fail("BuildException expected: output property not defined");
    } catch (BuildException ex) {
    }
  }
}
