class PlaceHold {
  private HashSet findLinks(Vector v) {
    HashSet result = new HashSet();
    for (int i = 0; i < v.size(); i++) {
      FileSet fs = ((FileSet) (v.get(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(getProject());
      String[][] fnd = new String[][] {ds.getIncludedFiles(), ds.getIncludedDirectories()};
      File dir = fs.getDir(getProject());
      for (int j = 0; j < fnd.length; j++) {
        for (int k = 0; k < fnd[j].length; k++) {
          try {
            File f = new File(dir, fnd[j][k]);
            File pf = f.getParentFile();
            String name = f.getName();
            if (SYMLINK_UTILS.isSymbolicLink(pf, name)) {
              result.add(new File(pf.getCanonicalFile(), name));
            }
          } catch (IOException e) {
            handleError(("IOException: " + fnd[j][k]) + " omitted");
          }
        }
      }
    }
    return result;
  }
}
