class PlaceHold {
  protected String[] getFiles(File basedir, DirectoryScanner ds) {
    return ds.getIncludedFiles();
  }
}
