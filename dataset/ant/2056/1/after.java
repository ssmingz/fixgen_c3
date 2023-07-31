class PlaceHold {
  @Test
  public void test3() {
    try {
      buildRule.executeTarget("test3");
      fail("BuildException expected: Manifest is invalid - no colon on header line");
    } catch (BuildException ex) {
      assertContains("Invalid Manifest", ex.getMessage());
    }
  }
}
