class PlaceHold {
  @Test
  public void testNoName() {
    try {
      buildRule.executeTarget("noName");
      fail("BuildException expected: required argument not specified");
    } catch (BuildException ex) {
    }
  }
}
