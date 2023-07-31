class SummaryXMLReport {
  public SummaryXMLReport(
      ProjectData projectData,
      File destinationDir,
      FileFinder finder,
      ComplexityCalculator complexity)
      throws IOException {
    File file = new File(destinationDir, "coverage-summary.xml");
    setPrintWriter(IOUtil.getPrintWriter(file));
    try {
      printHeader();
      printCoverageElement(projectData, complexity);
      increaseIndentation();
      println("<packages />");
      decreaseIndentation();
      println("</coverage>");
    } finally {
      close();
    }
  }
}
