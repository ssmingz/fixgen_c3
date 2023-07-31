class PlaceHold {
  @Test
  public void testBoth() {
    try {
      buildRule.executeTarget("testBoth");
      fail("Build exception expected: " + ParserSupports.ERROR_BOTH_ATTRIBUTES);
    } catch (BuildException ex) {
      assertEquals(ERROR_BOTH_ATTRIBUTES, ex.getMessage());
    }
  }
}
