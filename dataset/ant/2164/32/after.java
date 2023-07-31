class PlaceHold {
  protected void addSupportClasses(Hashtable ejbFiles) {
    Project project = task.getProject();
    for (Iterator i = config.supportFileSets.iterator(); i.hasNext(); ) {
      FileSet supportFileSet = ((FileSet) (i.next()));
      File supportBaseDir = supportFileSet.getDir();
      DirectoryScanner supportScanner = ScannerUtil.getDirectoryScanner(supportFileSet);
      supportScanner.scan();
      String[] supportFiles = supportScanner.getIncludedFiles();
      for (int j = 0; j < supportFiles.length; ++j) {
        ejbFiles.put(supportFiles[j], new File(supportBaseDir, supportFiles[j]));
      }
    }
  }
}
