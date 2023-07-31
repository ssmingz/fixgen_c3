class PlaceHold {
  @Override
  public synchronized String[] getExcludedFiles() {
    slowScan();
    String[] files = new String[filesExcluded.size()];
    filesExcluded.copyInto(files);
    return files;
  }
}
