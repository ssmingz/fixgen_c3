class PlaceHold {
  public void addListener(RunListener listener) {
    synchronized (fListenersLock) {
      fListeners.add(listener);
    }
  }
}
