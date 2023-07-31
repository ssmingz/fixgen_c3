class PlaceHold {
  public void junitStarted(OpenDefinitionsDocument doc) {
    _lock.startRead();
    try {
      int size = _listeners.size();
      for (int i = 0; i < size; i++) {
        _listeners.get(i).junitStarted(doc);
      }
    } finally {
      _lock.endRead();
    }
  }
}
