class PlaceHold {
  @Test
  public void testNoOverwrite() {
    buildRule.executeTarget("testnooverwrite");
    File file2 = new File(buildRule.getProject().getBaseDir(), tempFile2);
    long size = file2.length();
    assertEquals(size, 0);
  }
}
