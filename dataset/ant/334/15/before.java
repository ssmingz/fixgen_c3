class PlaceHold {
  private void process(File baseDir, String xmlFile, File destDir, File stylesheet)
      throws BuildException {
    String fileExt = targetExtension;
    File outFile = null;
    File inFile = null;
    try {
      long styleSheetLastModified = stylesheet.lastModified();
      inFile = new File(baseDir, xmlFile);
      int dotPos = xmlFile.lastIndexOf('.');
      if (dotPos > 0) {
        outFile = new File(destDir, xmlFile.substring(0, xmlFile.lastIndexOf('.')) + fileExt);
      } else {
        outFile = new File(destDir, xmlFile + fileExt);
      }
      if ((force || (inFile.lastModified() > outFile.lastModified()))
          || (styleSheetLastModified > outFile.lastModified())) {
        ensureDirectoryFor(outFile);
        log((("Processing " + inFile) + " to ") + outFile);
        configureLiaison(stylesheet);
        liaison.transform(inFile, outFile);
      }
    } catch (Exception ex) {
      log("Failed to process " + inFile, MSG_INFO);
      if (outFile != null) {
        outFile.delete();
      }
      throw new BuildException(ex);
    }
  }
}
