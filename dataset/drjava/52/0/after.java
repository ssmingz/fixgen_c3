class PlaceHold {
  public void clearHistory() {
    acquireWriteLock();
    try {
      _history.clear();
    } finally {
      releaseWriteLock();
    }
  }
}
