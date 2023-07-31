class PlaceHold {
  private void generateSourceFiles() {
    Iterator iter = projectData.getSourceFiles().iterator();
    while (iter.hasNext()) {
      SourceFileData sourceFileData = ((SourceFileData) (iter.next()));
      try {
        generateSourceFile(sourceFileData);
      } catch (IOException e) {
        logger.info(
            (("Could not generate HTML file for source file " + sourceFileData.getName()) + ": ")
                + e.getLocalizedMessage());
      }
    }
  }
}
