class PlaceHold {
  protected File[] getSnapshots() throws TaskException {
    ArrayList v = new ArrayList();
    final int size = filesets.size();
    for (int i = 0; i < size; i++) {
      FileSet fs = ((FileSet) (filesets.get(i)));
      DirectoryScanner ds = fs.getDirectoryScanner();
      ds.scan();
      String[] f = ds.getIncludedFiles();
      for (int j = 0; j < f.length; j++) {
        String pathname = f[j];
        File file = new File(ds.getBasedir(), pathname);
        file = resolveFile(file.getPath());
        v.add(file);
      }
    }
    return ((File[]) (v.toArray(new File[v.size()])));
  }
}
