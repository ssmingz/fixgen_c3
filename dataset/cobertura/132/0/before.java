class PlaceHold {
  public void testHTMLReportValidity() throws Exception {
    ProjectData.saveGlobalProjectData();
    String dataFileName = CoverageDataFileHandler.getDefaultDataFile().getAbsolutePath();
    String[] args =
        new String[] {
          "--format",
          "html",
          "--datafile",
          dataFileName,
          "--destination",
          PATH_TO_TEST_OUTPUT.getAbsolutePath(),
          PATH_TO_SOURCES.getAbsolutePath(),
          PATH_TO_SOURCES_2.getAbsolutePath()
        };
    Main.main(args);
    String htmlFiles[] =
        PATH_TO_TEST_OUTPUT.list(
            new FilenameFilter() {
              public boolean accept(File dir, String name) {
                return name.endsWith(".html");
              }
            });
    Arrays.sort(htmlFiles);
    assertTrue(htmlFiles.length >= 5);
    String[] requiredFiles =
        new String[] {
          "index.html",
          "help.html",
          "frame-packages.html",
          "frame-summary.html",
          "frame-sourcefiles.html"
        };
    for (int i = 0; i < requiredFiles.length; i++) {
      if (!containsFile(htmlFiles, requiredFiles[i])) {
        fail(("File " + requiredFiles[i]) + " not found among report files");
      }
    }
    String previousPrefix = "NONE";
    for (int i = 0; i < htmlFiles.length; i++) {
      if (containsFile(requiredFiles, htmlFiles[i]) || (!htmlFiles[i].startsWith(previousPrefix))) {
        JUnitXMLHelper.validate(new File(PATH_TO_TEST_OUTPUT, htmlFiles[i]));
      }
      if (htmlFiles[i].length() > 7) {
        previousPrefix = htmlFiles[i].substring(0, 7);
      } else {
        previousPrefix = htmlFiles[i];
      }
    }
    testSuccessful = true;
  }
}
