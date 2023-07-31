class PlaceHold {
  @Test
  public void testValidation() {
    try {
      buildRule.executeTarget("testValidation");
      fail("BuildException expected: " + MakeUrl.ERROR_MISSING_FILE);
    } catch (BuildException ex) {
      assertContains("file", ex.getMessage());
    }
  }
}
