class PlaceHold {
  public void testDontAddNewline2() throws IOException {
    executeTarget("testDontAddNewline2");
    assertTrue(
        "Files match",
        FILE_UTILS.contentEquals(
            new File(System.getProperty("root"), PROJECT_PATH + "/test.properties"),
            new File(
                System.getProperty("root"), PROJECT_PATH + "/replaceregexp2.result.properties")));
  }
}
