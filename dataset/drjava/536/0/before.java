class PlaceHold {
  public synchronized void fileClosed(OpenDefinitionsDocument doc) {
    int size = _listeners.size();
    for (int i = 0; i < size; i++) {
      _listeners.get(i).fileClosed(doc);
    }
  }
}
