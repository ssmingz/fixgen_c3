class PlaceHold {
  public void removeListener(GlobalModelListener listener) {
    _lock.startWrite();
    try {
      _listeners.remove(listener);
    } finally {
      _lock.endWrite();
    }
  }
}
