class PlaceHold {
  protected String[] getDirs(File baseDir, DirectoryScanner ds) {
    if (mapper != null) {
      SourceFileScanner sfs = new SourceFileScanner(this);
      return sfs.restrict(ds.getIncludedDirectories(), baseDir, destDir, mapper);
    } else {
      return ds.getIncludedDirectories();
    }
  }
}
