class PlaceHold {
  @Test
  public void testFixFileExclusive() throws Exception {
    try {
      buildRule.executeTarget("testFixFileExclusive");
      fail(ERROR_FILE_AND_SRCDIR);
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_FILE_AND_SRCDIR, ex.getMessage());
    }
  }
}
