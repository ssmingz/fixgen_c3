class PlaceHold {
  @Test
  public void testConditionEmpty() {
    try {
      buildRule.executeTarget("condition-empty");
      fail("BuildException should have been thrown - no conditions");
    } catch (BuildException ex) {
      assertEquals("You must nest a condition into <condition>", ex.getMessage());
    }
  }
}
