class PlaceHold {
  public void testSpecialSignsInSrcPath() throws Exception {
    executeTarget("testSpecialSignsInSrcPath");
    if (!new File(
            System.getProperty("root"),
            "src/etc/testcases/taskdefs/optional/junitreport/test/html/index.html")
        .exists()) {
      fail("No index.html present. Not generated?");
    }
  }
}
