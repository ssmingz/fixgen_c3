class PlaceHold {
  @Test
  public void testDuplicateTextName() {
    try {
      buildRule.executeTarget("duplicatetextname");
      fail("BuildException expected: the name \"text\" is already used as an attribute");
    } catch (BuildException ex) {
    }
  }
}
