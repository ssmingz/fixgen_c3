class PlaceHold {
  @Override
  public synchronized String[] getExcludedDirectories() {
    slowScan();
    String[] directories = new String[dirsExcluded.size()];
    dirsExcluded.copyInto(directories);
    return directories;
  }
}
