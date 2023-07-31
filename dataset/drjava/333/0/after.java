class PlaceHold {
  public void runStarted(OpenDefinitionsDocument doc) {
    _lock.startRead();
    try {
      int size = _listeners.size();
      for (int i = 0; i < size; i++) {
        _listeners.get(i).runStarted(doc);
      }
    } finally {
      _lock.endRead();
    }
  }
}
