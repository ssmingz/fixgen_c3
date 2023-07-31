class PlaceHold {
  public void setCurrentLocation(int loc) {
    acquireReadLock();
    try {
      synchronized (_reduced) {
        _setCurrentLocation(loc);
      }
    } finally {
      releaseReadLock();
    }
  }
}
