class PlaceHold {
  @Test
  public void testDuplicateElement() {
    try {
      buildRule.executeTarget("duplicate.element");
      fail("BuildException expected: the element text has already been specified");
    } catch (BuildException ex) {
    }
  }
}
