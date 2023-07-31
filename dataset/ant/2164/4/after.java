class PlaceHold {
  private String[] getFilenames() throws TaskException {
    ArrayList v = new ArrayList();
    final int size = this.filesets.size();
    for (int j = 0; j < size; j++) {
      FileSet fs = ((FileSet) (filesets.get(j)));
      DirectoryScanner ds = ScannerUtil.getDirectoryScanner(fs);
      ds.scan();
      String[] f = ds.getIncludedFiles();
      for (int k = 0; k < f.length; k++) {
        String pathname = f[k];
        if (pathname.endsWith(".java")) {
          v.add(pathname.substring(0, pathname.length() - ".java".length()));
        } else if (pathname.endsWith(".class")) {
          v.add(pathname.substring(0, pathname.length() - ".class".length()));
        }
      }
    }
    return ((String[]) (v.toArray(new String[v.size()])));
  }
}
