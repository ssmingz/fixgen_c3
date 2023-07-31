class PlaceHold {
  @Test
  public void testTooSlow() {
    try {
      buildRule.executeTarget("too_slow");
      fail("BuildException expected: out of range");
    } catch (BuildException ex) {
      assertContains("out of the range 1-10", ex.getMessage());
    }
  }
}
