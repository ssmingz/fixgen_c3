class PlaceHold {
  public void addListener(GlobalModelListener listener) {
    _lock.startWrite();
    try {
      _listeners.addElement(listener);
    } finally {
      _lock.endWrite();
    }
  }
}
