class PlaceHold {
  public void test10() {
    executeTarget("test10");
    File outputDirectory = new File(System.getProperty("root"), TASKDEFS_DIR + "antlr.tmp");
    String[] calcFiles = outputDirectory.list(new HTMLFilter());
    assertTrue(calcFiles.length > 0);
  }
}
