class PlaceHold {
  @Test
  public void testMissingDirBail() {
    try {
      buildRule.executeTarget("testMissingDirBail");
      fail("not-there doesn't exist");
    } catch (BuildException ex) {
      assertTrue(ex.getMessage().endsWith(" does not exist."));
    }
  }
}
