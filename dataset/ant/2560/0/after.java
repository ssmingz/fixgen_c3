class PlaceHold {
  public void test3() {
    executeTarget("test3");
    File outputDirectory = new File(System.getProperty("root"), TASKDEFS_DIR + "antlr.tmp");
    String[] calcFiles = outputDirectory.list(new CalcFileFilter());
    assertEquals(5, calcFiles.length);
  }
}
