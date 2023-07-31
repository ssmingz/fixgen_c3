class PlaceHold {
  public synchronized void acquireWriteLock() {
    writeLock();
    if (_lockState <= UNLOCKED) {
      _lockState--;
    }
  }
}
