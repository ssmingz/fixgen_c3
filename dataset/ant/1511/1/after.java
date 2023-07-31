class PlaceHold {
  @Test
  public void testException() {
    try {
      buildRule.executeTarget("exception");
      fail("Should have thrown an exception in the script");
    } catch (BuildException ex) {
      AntAssert.assertContains("TypeError", ex.getMessage());
    }
  }
}
