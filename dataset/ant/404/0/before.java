class PlaceHold {
  public String[] getIncludedFiles() {
    String[] files = new String[filesIncluded.size()];
    filesIncluded.copyInto(files);
    Arrays.sort(files);
    return files;
  }
}
