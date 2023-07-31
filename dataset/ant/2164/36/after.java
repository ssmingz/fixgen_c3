class PlaceHold {
  protected void addFiles(ArrayList filesets, ZipOutputStream zOut)
      throws IOException, TaskException {
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.get(i)));
      DirectoryScanner ds = ScannerUtil.getDirectoryScanner(fs);
      String prefix = "";
      String fullpath = "";
      if (fs instanceof ZipFileSet) {
        ZipFileSet zfs = ((ZipFileSet) (fs));
        prefix = zfs.getPrefix();
        fullpath = zfs.getFullpath();
      }
      if (((prefix.length() > 0) && (!prefix.endsWith("/"))) && (!prefix.endsWith("\\"))) {
        prefix += "/";
      }
      if (prefix.length() > 0) {
        addParentDirs(null, prefix, zOut, "");
        zipDir(null, zOut, prefix);
      } else if (fullpath.length() > 0) {
        addParentDirs(null, fullpath, zOut, "");
      }
      if ((fs instanceof ZipFileSet) && (((ZipFileSet) (fs)).getSrc() != null)) {
        addZipEntries(((ZipFileSet) (fs)), ds, zOut, prefix, fullpath);
      } else {
        addFiles(ds, zOut, prefix, fullpath);
      }
    }
  }
}
