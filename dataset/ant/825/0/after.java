class PlaceHold {
  @Test
  public void testSpecialSignsInHtmlPath() throws Exception {
    buildRule.executeTarget("testSpecialSignsInHtmlPath");
    File reportFile = new File(buildRule.getOutputDir(), "html# $%§&-!report/index.html");
    assertTrue("No index.html present. Not generated?", reportFile.exists());
    assertTrue("Cant read the report file.", reportFile.canRead());
    assertTrue("File shouldn't be empty.", reportFile.length() > 0);
    URL reportUrl = new URL(FileUtils.getFileUtils().toURI(reportFile.getAbsolutePath()));
    InputStream reportStream = reportUrl.openStream();
    assertTrue("This shouldn't be an empty stream.", reportStream.available() > 0);
  }
}
