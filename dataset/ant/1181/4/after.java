class PlaceHold {
  @Test
  public void testEmpty() {
    try {
      buildRule.executeTarget("testEmpty");
      fail("BuildException expected: must fail");
    } catch (BuildException ex) {
      assertContains("No nested XML specified", ex.getMessage());
    }
  }
}
