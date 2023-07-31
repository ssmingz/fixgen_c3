class PlaceHold {
  @Test
  public void testNolanguage() {
    try {
      buildRule.executeTarget("testNolanguage");
      fail("Absence of language attribute not detected");
    } catch (BuildException ex) {
      assertContains("script language must be specified", ex.getMessage());
    }
  }
}
