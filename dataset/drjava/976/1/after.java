class PlaceHold {
  public void clearRegions() {
    for (OpenDefinitionsDocument doc : _documents) {
      for (final R region : _regions.get(doc)) {
        Utilities.invokeLater(
            new Runnable() {
              public void run() {
                _lock.startRead();
                try {
                  for (RegionManagerListener<R> l : _listeners) {
                    l.regionRemoved(region);
                  }
                } finally {
                  _lock.endRead();
                }
              }
            });
      }
    }
    _regions.clear();
    _documents.clear();
  }
}
