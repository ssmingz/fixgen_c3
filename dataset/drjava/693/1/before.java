class PlaceHold {
  public void removeListener(GlobalModelListener listener) {
    _lock.startWrite();
    try {
      _listeners.removeElement(listener);
    } finally {
      _lock.endWrite();
    }
  }
}
