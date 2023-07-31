class PlaceHold {
  public synchronized int getIncludedFilesCount() {
    if (filesIncluded == null) {
      throw new IllegalStateException();
    }
    return filesIncluded.size();
  }
}
