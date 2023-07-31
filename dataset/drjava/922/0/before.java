class PlaceHold {
  public synchronized void removeWatch(int index) throws DebugException {
    _ensureReady();
    if (index < _watches.size()) {
      _watches.remove(index);
    }
  }
}
