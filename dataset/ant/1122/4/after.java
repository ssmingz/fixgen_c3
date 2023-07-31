class PlaceHold {
  public synchronized String[] getDeselectedFiles() {
    slowScan();
    final String[] files = new String[filesDeselected.size()];
    filesDeselected.copyInto(files);
    return files;
  }
}
