class PlaceHold {
  public void testWithStyleFromClasspath() throws Exception {
    executeTarget("testWithStyleFromClasspath");
    File reportFile = new File(getOutputDir(), "html/index.html");
    assertTrue("No index.html present. Not generated?", reportFile.exists());
    assertTrue("Cant read the report file.", reportFile.canRead());
    assertTrue("File shouldnt be empty.", reportFile.length() > 0);
    URL reportUrl = new URL(FileUtils.getFileUtils().toURI(reportFile.getAbsolutePath()));
    InputStream reportStream = reportUrl.openStream();
    assertTrue("This shouldnt be an empty stream.", reportStream.available() > 0);
  }
}
