class PlaceHold {
  public void newFileCreated(OpenDefinitionsDocument doc) {
    _lock.startRead();
    try {
      int size = _listeners.size();
      for (int i = 0; i < size; i++) {
        _listeners.get(i).newFileCreated(doc);
      }
    } finally {
      _lock.endRead();
    }
  }
}
