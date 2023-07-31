class PlaceHold {
  @Test
  public void testPropertyNoValue() {
    try {
      buildRule.executeTarget("testPropertyNoValue");
      fail("Build exception expected: " + ParserSupports.ERROR_NO_VALUE);
    } catch (BuildException ex) {
      assertEquals(ERROR_NO_VALUE, ex.getMessage());
    }
  }
}
