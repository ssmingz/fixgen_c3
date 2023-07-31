class PlaceHold {
  @Test
  public void testBadURL() throws Exception {
    try {
      buildRule.executeTarget("testBadURL");
      fail("Build exception expected: error in URL");
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_BAD_URL, ex.getMessage());
    }
  }
}
