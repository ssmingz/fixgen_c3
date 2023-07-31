class PlaceHold {
  public synchronized String[] getDeselectedDirectories() {
    slowScan();
    final String[] directories = new String[dirsDeselected.size()];
    dirsDeselected.copyInto(directories);
    return directories;
  }
}
