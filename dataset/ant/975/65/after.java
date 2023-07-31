class PlaceHold {
  protected Hashtable scanFileSets() throws TaskException {
    Hashtable files = new Hashtable();
    for (int i = 0; i < m_fileSets.size(); i++) {
      FileSet fs = ((FileSet) (m_fileSets.get(i)));
      DirectoryScanner ds = ScannerUtil.getDirectoryScanner(fs);
      ds.scan();
      String[] f = ds.getIncludedFiles();
      getContext()
          .debug((((i + ") Adding ") + f.length) + " files from directory ") + ds.getBasedir());
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
