class PlaceHold {
  @Test
  public void testIsfalseIncomplete1() {
    try {
      buildRule.executeTarget("isfalse-incomplete");
      fail("BuildException should have been thrown - Missing attribute");
    } catch (BuildException ex) {
      assertEquals("Nothing to test for falsehood", ex.getMessage());
    }
  }
}
