class PlaceHold {
  @Test
  public void testDuplicateAttribute() {
    try {
      buildRule.executeTarget("duplicate.attribute");
      fail("BuildException expected: the attribute text has already been specified");
    } catch (BuildException ex) {
    }
  }
}
