class PlaceHold {
  public void assertIndexCreated() {
    if (!new File(
            System.getProperty("root"),
            "src/etc/testcases/taskdefs/optional/junitreport/test/html/index.html")
        .exists()) {
      fail("No file index file found");
    }
  }
}
