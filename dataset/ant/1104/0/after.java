class PlaceHold {
  @Test
  public void testFilesmatchIncomplete() {
    try {
      buildRule.executeTarget("filesmatch-incomplete");
      fail("Build exception should have been thrown - Missing file2 attirbute");
    } catch (BuildException ex) {
      assertEquals("both file1 and file2 are required in filesmatch", ex.getMessage());
    }
  }
}
