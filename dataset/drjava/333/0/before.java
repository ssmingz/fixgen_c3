class PlaceHold {
  public synchronized void runStarted(OpenDefinitionsDocument doc) {
    int size = _listeners.size();
    for (int i = 0; i < size; i++) {
      _listeners.get(i).runStarted(doc);
    }
  }
}
