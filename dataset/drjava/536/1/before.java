class PlaceHold {
  public synchronized void newFileCreated(OpenDefinitionsDocument doc) {
    int size = _listeners.size();
    for (int i = 0; i < size; i++) {
      _listeners.get(i).newFileCreated(doc);
    }
  }
}
