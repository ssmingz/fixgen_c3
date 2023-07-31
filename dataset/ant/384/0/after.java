class PlaceHold {
  @Test
  public void testNoFile() {
    try {
      buildRule.executeTarget("testNoFile");
      fail("BuildException expected: missing file");
    } catch (BuildException ex) {
      assertContains("file", ex.getMessage());
    }
  }
}
