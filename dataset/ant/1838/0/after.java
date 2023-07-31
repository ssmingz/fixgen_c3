class PlaceHold {
  @Test
  public void testEmpty() {
    try {
      buildRule.executeTarget("empty");
      fail("BuildException expected: required argument not specified");
    } catch (BuildException ex) {
    }
  }
}
