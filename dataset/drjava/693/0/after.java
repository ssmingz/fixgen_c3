class PlaceHold {
  public void addListener(GlobalModelListener listener) {
    _lock.startWrite();
    try {
      _listeners.add(listener);
    } finally {
      _lock.endWrite();
    }
  }
}
