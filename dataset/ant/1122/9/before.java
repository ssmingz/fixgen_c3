class PlaceHold {
  @Override
  public synchronized String[] getNotIncludedFiles() {
    slowScan();
    String[] files = new String[filesNotIncluded.size()];
    filesNotIncluded.copyInto(files);
    return files;
  }
}
