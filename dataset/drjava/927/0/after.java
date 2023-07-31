class PlaceHold {
  public String getHistoryAsStringWithSemicolons() {
    acquireReadLock();
    try {
      return _history.getHistoryAsStringWithSemicolons();
    } finally {
      releaseReadLock();
    }
  }
}
