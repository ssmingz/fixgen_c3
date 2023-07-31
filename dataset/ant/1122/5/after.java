class PlaceHold {
  public synchronized String[] getExcludedDirectories() {
    slowScan();
    final String[] directories = new String[dirsExcluded.size()];
    dirsExcluded.copyInto(directories);
    return directories;
  }
}
