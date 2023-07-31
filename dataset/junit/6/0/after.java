class PlaceHold {
  public void addFirstListener(RunListener listener) {
    synchronized (fListenersLock) {
      fListeners.add(0, listener);
    }
  }
}
