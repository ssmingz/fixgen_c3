class PlaceHold {
  public synchronized void removeWatch(int index) throws DebugException {
    if (index < _watches.size()) {
      final DebugWatchData watch = _watches.get(index);
      _watches.remove(index);
      Utilities.invokeLater(
          new Runnable() {
            public void run() {
              _notifier.watchRemoved(watch);
            }
          });
    }
  }
}
