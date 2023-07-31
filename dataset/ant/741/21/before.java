class PlaceHold {
  protected File[] getSnapshots() {
    Vector v = new Vector();
    final int size = filesets.size();
    for (int i = 0; i < size; i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(getProject());
      ds.scan();
      String[] f = ds.getIncludedFiles();
      for (int j = 0; j < f.length; j++) {
        String pathname = f[j];
        File file = new File(ds.getBasedir(), pathname);
        file = project.resolveFile(file.getPath());
        v.addElement(file);
      }
    }
    File[] files = new File[v.size()];
    v.copyInto(files);
    return files;
  }
}
