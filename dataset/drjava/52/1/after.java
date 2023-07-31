class PlaceHold {
  public void addToHistory(String text) {
    acquireWriteLock();
    try {
      _history.add(text);
    } finally {
      releaseWriteLock();
    }
  }
}
