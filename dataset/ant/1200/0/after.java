class PlaceHold {
  @Test
  public void testBadTimeout() throws Exception {
    try {
      buildRule.executeTarget("testBadTimeout");
      fail("Build exception expected: error on -ve timeout");
    } catch (BuildException ex) {
      assertEquals(ERROR_BAD_TIMEOUT, ex.getMessage());
    }
  }
}
