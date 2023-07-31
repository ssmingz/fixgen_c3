class PlaceHold {
  public void testXMLReportValidity() throws Exception {
    String[] args;
    ProjectData.saveGlobalProjectData();
    String dataFileName = CoverageDataFileHandler.getDefaultDataFile().getAbsolutePath();
    args =
        new String[] {
          "--format",
          "xml",
          "--datafile",
          dataFileName,
          "--destination",
          PATH_TO_TEST_OUTPUT,
          PATH_TO_SOURCE_CODE
        };
    Main.main(args);
    JUnitXMLHelper.readXmlFile(new File(PATH_TO_XML_REPORT), true);
  }
}
