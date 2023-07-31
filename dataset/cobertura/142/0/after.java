class XMLReport {
  public XMLReport(
      ProjectData projectData,
      File destinationDir,
      FileFinder finder,
      ComplexityCalculator complexity)
      throws IOException {
    this.complexity = complexity;
    this.finder = finder;
    File file = new File(destinationDir, "coverage.xml");
    setPrintWriter(IOUtil.getPrintWriter(file));
    try {
      printHeader();
      printCoverageElement(projectData, complexity);
      increaseIndentation();
      dumpSources();
      dumpPackages(projectData);
      decreaseIndentation();
      println("</coverage>");
    } finally {
      close();
    }
  }
}
