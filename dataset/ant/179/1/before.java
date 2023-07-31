class PlaceHold {
  public void testFileResourceWithFilter() {
    executeTarget("testFileResourceWithFilter");
    File file1 = new File(getProject().getProperty("to.dir") + "/fileNR.txt");
    assertTrue(file1.exists());
    try {
      String file1Content = FileUtils.readFully(new FileReader(file1));
      assertEquals("This is file 42", file1Content);
    } catch (IOException e) {
    }
  }
}
