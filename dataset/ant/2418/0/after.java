class PlaceHold {
  @Test
  public void testConditionIncomplete() {
    try {
      buildRule.executeTarget("condition-incomplete");
      fail("BuildException should have been thrown - property attribute has been omitted");
    } catch (BuildException ex) {
      assertEquals("The property attribute is required.", ex.getMessage());
    }
  }
}
