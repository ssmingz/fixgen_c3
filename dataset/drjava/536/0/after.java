class PlaceHold {
  public void fileClosed(OpenDefinitionsDocument doc) {
    _lock.startRead();
    try {
      int size = _listeners.size();
      for (int i = 0; i < size; i++) {
        _listeners.get(i).fileClosed(doc);
      }
    } finally {
      _lock.endRead();
    }
  }
}
