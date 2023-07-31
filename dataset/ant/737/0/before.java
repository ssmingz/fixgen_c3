class PlaceHold {
  public void testSpecialSignsInHtmlPath() throws Exception {
    executeTarget("testSpecialSignsInHtmlPath");
    if (!new File(
            System.getProperty("root"),
            "src/etc/testcases/taskdefs/optional/junitreport/test/html# $%�&-!report/index.html")
        .exists()) {
      fail("No index.html present. Not generated?");
    }
  }
}
