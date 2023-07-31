class PlaceHold {
  public synchronized void removeListener(GlobalModelListener listener) {
    _listeners.remove(listener);
  }
}
