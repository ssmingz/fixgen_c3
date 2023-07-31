class PlaceHold {
  @Test
  public void testNoSource() {
    try {
      buildRule.executeTarget("testnosource");
      fail("Build exception expected: No source specified");
    } catch (BuildException ex) {
      assertContains("srcdir attribute must be set", ex.getMessage());
    }
  }
}
