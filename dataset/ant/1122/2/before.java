class PlaceHold {
  @Override
  public synchronized String[] getDeselectedDirectories() {
    slowScan();
    String[] directories = new String[dirsDeselected.size()];
    dirsDeselected.copyInto(directories);
    return directories;
  }
}
