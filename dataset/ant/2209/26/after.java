class PlaceHold {
  @Test
  public void testNoClassname() {
    try {
      buildRule.executeTarget("noClassname");
      fail("BuildException expected: required argument not specified");
    } catch (BuildException ex) {
    }
  }
}
