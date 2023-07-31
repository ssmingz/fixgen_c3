class PlaceHold {
  public void move(int dist) {
    acquireReadLock();
    try {
      synchronized (_reduced) {
        _move(dist);
      }
    } finally {
      releaseReadLock();
    }
  }
}
