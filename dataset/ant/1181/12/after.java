class PlaceHold {
  @Test
  public void testTooFast() {
    try {
      buildRule.executeTarget("too_fast");
      fail("BuildException expected: out of range");
    } catch (BuildException ex) {
      assertContains("out of the range 1-10", ex.getMessage());
    }
  }
}
