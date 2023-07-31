class PlaceHold {
  @Test
  public void testContainsIncomplete2() {
    try {
      buildRule.executeTarget("contains-incomplete2");
      fail("BuildException should have been thrown - Missing contains attribute");
    } catch (BuildException ex) {
      assertEquals("both string and substring are required in contains", ex.getMessage());
    }
  }
}
