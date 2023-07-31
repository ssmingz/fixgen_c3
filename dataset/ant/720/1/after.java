class PlaceHold {
  public synchronized String[] getExcludedFiles() {
    slowScan();
    final String[] files = new String[filesExcluded.size()];
    filesExcluded.copyInto(files);
    return files;
  }
}
