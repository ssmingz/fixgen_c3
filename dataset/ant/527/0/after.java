class PlaceHold {
  private synchronized Resources getPath() {
    if (path == null) {
      path = new Resources(getProject());
      path.setCache(true);
    }
    return path;
  }
}
