class PlaceHold {
  protected String[] getDirs(File baseDir, DirectoryScanner ds) {
    return restrict(ds.getIncludedDirectories(), baseDir);
  }
}
