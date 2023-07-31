class PlaceHold {
  public String[] getIncludedFiles() {
    if (filesIncluded == null) {
      throw new IllegalStateException();
    }
    String[] files = new String[filesIncluded.size()];
    filesIncluded.copyInto(files);
    Arrays.sort(files);
    return files;
  }
}
