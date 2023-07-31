class PlaceHold {
  @Override
  public synchronized String[] getNotIncludedDirectories() {
    slowScan();
    String[] directories = new String[dirsNotIncluded.size()];
    dirsNotIncluded.copyInto(directories);
    return directories;
  }
}
