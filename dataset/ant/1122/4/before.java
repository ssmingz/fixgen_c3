class PlaceHold {
  @Override
  public synchronized String[] getDeselectedFiles() {
    slowScan();
    String[] files = new String[filesDeselected.size()];
    filesDeselected.copyInto(files);
    return files;
  }
}
