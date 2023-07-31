class PlaceHold {
  public void testDontAddNewline1() throws IOException {
    executeTarget("testDontAddNewline1");
    assertTrue(
        "Files match",
        FileUtils.newFileUtils()
            .contentEquals(
                new File(System.getProperty("root"), PROJECT_PATH + "/test.properties"),
                new File(
                    System.getProperty("root"),
                    PROJECT_PATH + "/replaceregexp2.result.properties")));
  }
}
