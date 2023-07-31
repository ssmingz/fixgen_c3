class PlaceHold {
  @Test
  public void testOverwrite() {
    buildRule.executeTarget("testoverwrite");
    File file2 = new File(buildRule.getProject().getBaseDir(), tempFile2);
    long size = file2.length();
    assertTrue(size > 0);
  }
}
