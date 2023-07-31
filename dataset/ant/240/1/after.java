class PlaceHold {
  @Test
  public void testEmptySource() {
    try {
      buildRule.executeTarget("testemptysource");
      fail("Build exception expected: No source specified");
    } catch (BuildException ex) {
      assertContains("srcdir attribute must be non-empty", ex.getMessage());
    }
  }
}
