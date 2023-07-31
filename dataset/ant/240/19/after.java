class PlaceHold {
  @Test
  public void testFail() {
    try {
      buildRule.executeTarget("testFail");
      fail("BuildException expected: must fail");
    } catch (BuildException ex) {
      assertContains("${foo}=bar", ex.getMessage());
    }
  }
}
