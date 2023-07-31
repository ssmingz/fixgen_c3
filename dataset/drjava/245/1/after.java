class PlaceHold {
  public synchronized void releaseWriteLock() {
    writeUnlock();
    if (_lockState < UNLOCKED) {
      _lockState++;
    }
  }
}
