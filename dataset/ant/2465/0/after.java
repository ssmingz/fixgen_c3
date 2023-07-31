class PlaceHold {
  @Test
  public void testNoSourcefileDefined() {
    try {
      buildRule.executeTarget("testNoSourcefileDefined");
      fail("BuildException expected: source file not defined");
    } catch (BuildException ex) {
    }
  }
}
