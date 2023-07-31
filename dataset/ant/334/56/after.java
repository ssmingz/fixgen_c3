class PlaceHold {
  public synchronized int getIncludedDirsCount() {
    if (dirsIncluded == null) {
      throw new IllegalStateException("Must call scan() first");
    }
    return dirsIncluded.size();
  }
}
