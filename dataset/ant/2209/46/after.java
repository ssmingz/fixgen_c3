class PlaceHold {
  @Test
  public void testNoFile() {
    try {
      buildRule.executeTarget("testNoFile");
      fail("BuildException expected: file is required");
    } catch (BuildException ex) {
    }
  }
}
