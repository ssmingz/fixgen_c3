class PlaceHold {
  public synchronized int getIncludedFilesCount() {
    if (filesIncluded == null) {
      throw new IllegalStateException("Must call scan() first");
    }
    return filesIncluded.size();
  }
}
