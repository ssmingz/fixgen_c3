class PlaceHold {
  protected String[] getFiles(File baseDir, DirectoryScanner ds) {
    return restrict(ds.getIncludedFiles(), baseDir);
  }
}
