class PlaceHold {
  @Test
  public void testNoFile() throws Exception {
    try {
      buildRule.executeTarget("testNoFile");
      fail("No file at file attribute");
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_NO_FILE, ex.getMessage());
    }
  }
}
