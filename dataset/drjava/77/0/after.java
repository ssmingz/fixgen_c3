class PlaceHold {
  public synchronized void acquireReadLock() {
    readLock();
    if (_lockState >= UNLOCKED) {
      _lockState++;
    }
  }
}
