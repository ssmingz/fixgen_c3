class PlaceHold {
  public synchronized String[] getNotIncludedDirectories() {
    slowScan();
    final String[] directories = new String[dirsNotIncluded.size()];
    dirsNotIncluded.copyInto(directories);
    return directories;
  }
}
