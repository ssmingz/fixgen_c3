class PlaceHold {
  protected Hashtable scanFileSets() {
    Hashtable files = new Hashtable();
    for (int i = 0; i < fileSets.size(); i++) {
      FileSet fs = ((FileSet) (fileSets.elementAt(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(getProject());
      ds.scan();
      String[] f = ds.getIncludedFiles();
      log(
          (((i + ") Adding ") + f.length) + " files from directory ") + ds.getBasedir(),
          MSG_VERBOSE);
      for (int j = 0; j < f.length; j++) {
        String pathname = f[j];
        if (pathname.endsWith(".java")) {
          File file = new File(ds.getBasedir(), pathname);
          String classname = pathname.substring(0, pathname.length() - ".java".length());
          classname = classname.replace(File.separatorChar, '.');
          files.put(file.getAbsolutePath(), classname);
        }
      }
    }
    return files;
  }
}
