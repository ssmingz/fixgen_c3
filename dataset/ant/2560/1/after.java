class PlaceHold {
  public void testNoFileJUnitNoFrames() {
    executeTarget("reports1");
    if (new File(
            System.getProperty("root"),
            "src/etc/testcases/taskdefs/optional/junitreport/test/html/junit-noframes.html")
        .exists()) {
      fail("No file junit-noframes.html expected");
    }
  }
}
