class PlaceHold {
  public synchronized String[] getNotIncludedFiles() {
    slowScan();
    final String[] files = new String[filesNotIncluded.size()];
    filesNotIncluded.copyInto(files);
    return files;
  }
}
