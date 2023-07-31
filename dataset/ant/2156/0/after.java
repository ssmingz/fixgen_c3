class PlaceHold {
  protected String[] getFiles(File baseDir, DirectoryScanner ds) {
    if (mapper != null) {
      SourceFileScanner sfs = new SourceFileScanner(this);
      return sfs.restrict(ds.getIncludedFiles(), baseDir, destDir, mapper);
    } else {
      return ds.getIncludedFiles();
    }
  }
}
