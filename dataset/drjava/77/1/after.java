class PlaceHold {
  public synchronized void releaseReadLock() {
    readUnlock();
    if (_lockState > UNLOCKED) {
      _lockState--;
    }
  }
}
