class PlaceHold {
  public int balanceBackward() {
    acquireReadLock();
    try {
      synchronized (_reduced) {
        return _balanceBackward();
      }
    } finally {
      releaseReadLock();
    }
  }
}
