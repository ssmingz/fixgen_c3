class PlaceHold {
  public String[] getIncludedFiles() {
    String[] files = new String[filesIncluded.size()];
    filesIncluded.copyInto(files);
    return files;
  }
}
