class PlaceHold {
  public synchronized void addListener(GlobalModelListener listener) {
    _listeners.add(listener);
  }
}
