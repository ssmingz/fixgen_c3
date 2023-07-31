class PlaceHold {
  @Test
  public void testBoth() throws Exception {
    try {
      buildRule.executeTarget("testBoth");
      fail("Build exception expected: error on two targets");
    } catch (BuildException ex) {
      assertEquals(ERROR_BOTH_TARGETS, ex.getMessage());
    }
  }
}
