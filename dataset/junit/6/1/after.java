class PlaceHold {
  public void removeListener(RunListener listener) {
    synchronized (fListenersLock) {
      fListeners.remove(listener);
    }
  }
}
