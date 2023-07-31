class PlaceHold {
  @Test
  public void testIstrueIncomplete1() {
    try {
      buildRule.executeTarget("istrue-incomplete");
      fail("BuildException should have been thrown - Missing attribute");
    } catch (BuildException ex) {
      assertEquals("Nothing to test for truth", ex.getMessage());
    }
  }
}
