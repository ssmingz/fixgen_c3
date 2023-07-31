class PlaceHold {
  @Test
  public void testEmpty() {
    try {
      buildRule.executeTarget("testEmpty");
      fail("Build exception expected: " + ParserSupports.ERROR_NO_ATTRIBUTES);
    } catch (BuildException ex) {
      assertEquals(ERROR_NO_ATTRIBUTES, ex.getMessage());
    }
  }
}
