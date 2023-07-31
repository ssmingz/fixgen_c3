class PlaceHold {
  public synchronized void removeWatch(String field) throws DebugException {
    _ensureReady();
    for (int i = 0; i < _watches.size(); i++) {
      DebugWatchData watch = _watches.get(i);
      if (watch.getName().equals(field)) {
        _watches.remove(i);
      }
    }
  }
}
