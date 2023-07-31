class PlaceHold {
  @Test
  public void test4() {
    try {
      buildRule.executeTarget("test4");
      fail("BuildException expected: Manifest is invalid - section starts with continuation line");
    } catch (BuildException ex) {
      assertContains("Invalid Manifest", ex.getMessage());
    }
  }
}
