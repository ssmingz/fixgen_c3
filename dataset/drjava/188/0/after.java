class PlaceHold {
  public int balanceForward() {
    acquireReadLock();
    try {
      synchronized (_reduced) {
        return _balanceForward();
      }
    } finally {
      releaseReadLock();
    }
  }
}
