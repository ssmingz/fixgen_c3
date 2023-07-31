class PlaceHold {
  @Test
  public void testMissingFileBail() {
    try {
      buildRule.executeTarget("testMissingFileBail");
      fail("not-there doesn't exist");
    } catch (BuildException ex) {
      assertTrue(ex.getMessage().startsWith("Warning: Could not find file "));
    }
  }
}
