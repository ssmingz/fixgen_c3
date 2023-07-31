class PlaceHold {
  public synchronized int getIncludedDirsCount() {
    if (dirsIncluded == null) {
      throw new IllegalStateException();
    }
    return dirsIncluded.size();
  }
}
