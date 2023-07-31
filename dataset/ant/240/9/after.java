class PlaceHold {
  @Test
  public void testNoSourcefilefound() {
    try {
      buildRule.executeTarget("testNoSourcefilefound");
      fail("BuildException expected: File not found");
    } catch (BuildException ex) {
      assertContains(" doesn't exist", ex.getMessage());
    }
  }
}
