class PlaceHold {
  private void process(File inFile, File outFile, Resource stylesheet) throws BuildException {
    try {
      long styleSheetLastModified = stylesheet.getLastModified();
      log((("In file " + inFile) + " time: ") + inFile.lastModified(), MSG_DEBUG);
      log((("Out file " + outFile) + " time: ") + outFile.lastModified(), MSG_DEBUG);
      log((("Style file " + xslFile) + " time: ") + styleSheetLastModified, MSG_DEBUG);
      if ((force || (inFile.lastModified() >= outFile.lastModified()))
          || (styleSheetLastModified >= outFile.lastModified())) {
        ensureDirectoryFor(outFile);
        log((("Processing " + inFile) + " to ") + outFile, MSG_INFO);
        configureLiaison(stylesheet);
        liaison.transform(inFile, outFile);
      } else {
        log(
            (((("Skipping input file " + inFile) + " because it is older than output file ")
                        + outFile)
                    + " and so is the stylesheet ")
                + stylesheet,
            MSG_DEBUG);
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
