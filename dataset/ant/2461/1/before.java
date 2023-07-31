class PlaceHold {
  private void process(File baseDir, String xmlFile, File destDir, Resource stylesheet)
      throws BuildException {
    File outF = null;
    File inF = null;
    try {
      long styleSheetLastModified = stylesheet.getLastModified();
      inF = new File(baseDir, xmlFile);
      if (inF.isDirectory()) {
        log(("Skipping " + inF) + " it is a directory.", MSG_VERBOSE);
        return;
      }
      FileNameMapper mapper = null;
      if (mapperElement != null) {
        mapper = mapperElement.getImplementation();
      } else {
        mapper = new StyleMapper();
      }
      String[] outFileName = mapper.mapFileName(xmlFile);
      if ((outFileName == null) || (outFileName.length == 0)) {
        log(("Skipping " + inFile) + " it cannot get mapped to output.", MSG_VERBOSE);
        return;
      } else if ((outFileName == null) || (outFileName.length > 1)) {
        log(("Skipping " + inFile) + " its mapping is ambiguos.", MSG_VERBOSE);
        return;
      }
      outF = new File(destDir, outFileName[0]);
      if ((force || (inF.lastModified() > outF.lastModified()))
          || (styleSheetLastModified > outF.lastModified())) {
        ensureDirectoryFor(outF);
        log((("Processing " + inF) + " to ") + outF);
        configureLiaison(stylesheet);
        liaison.transform(inF, outF);
      }
    } catch (Exception ex) {
      log("Failed to process " + inFile, MSG_INFO);
      if (outF != null) {
        outF.delete();
      }
      throw new BuildException(ex);
    }
  }
}
