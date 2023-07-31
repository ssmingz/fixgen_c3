class PlaceHold {
  public void testWithParams() throws Exception {
    expectLogContaining("testWithParams", "key1=value1,key2=value2");
    File reportFile =
        new File(
            System.getProperty("root"),
            "src/etc/testcases/taskdefs/optional/junitreport/test/html/index.html");
    assertTrue("No index.html present. Not generated?", reportFile.exists());
    assertTrue("Cant read the report file.", reportFile.canRead());
    assertTrue("File shouldnt be empty.", reportFile.length() > 0);
    URL reportUrl = new URL(FileUtils.getFileUtils().toURI(reportFile.getAbsolutePath()));
    InputStream reportStream = reportUrl.openStream();
    assertTrue("This shouldnt be an empty stream.", reportStream.available() > 0);
  }
}
