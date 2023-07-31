class PlaceHold {
  public String getHistoryAsString() {
    acquireReadLock();
    try {
      return _history.getHistoryAsString();
    } finally {
      releaseReadLock();
    }
  }
}
