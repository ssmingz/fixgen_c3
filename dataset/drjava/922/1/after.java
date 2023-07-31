class PlaceHold {
  public synchronized void removeWatch(String field) throws DebugException {
    for (int i = 0; i < _watches.size(); i++) {
      final DebugWatchData watch = _watches.get(i);
      if (watch.getName().equals(field)) {
        _watches.remove(i);
        Utilities.invokeLater(
            new Runnable() {
              public void run() {
                _notifier.watchRemoved(watch);
              }
            });
      }
    }
  }
}
