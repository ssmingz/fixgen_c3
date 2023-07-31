class PlaceHold {
  public synchronized void junitStarted(OpenDefinitionsDocument doc) {
    int size = _listeners.size();
    for (int i = 0; i < size; i++) {
      _listeners.get(i).junitStarted(doc);
    }
  }
}
