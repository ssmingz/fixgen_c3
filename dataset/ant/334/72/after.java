class PlaceHold {
  public synchronized String[] getIncludedFiles() {
    if (filesIncluded == null) {
      throw new IllegalStateException("Must call scan() first");
    }
    String[] files = new String[filesIncluded.size()];
    filesIncluded.copyInto(files);
    Arrays.sort(files);
    return files;
  }
}
