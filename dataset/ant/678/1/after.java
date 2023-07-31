class PlaceHold {
  @Test
  public void testNegationIncomplete() {
    try {
      buildRule.executeTarget("negationincomplete");
      fail("BuildException should have been thrown - no conditions in <not>");
    } catch (BuildException ex) {
      assertEquals("You must nest a condition into <not>", ex.getMessage());
    }
  }
}
