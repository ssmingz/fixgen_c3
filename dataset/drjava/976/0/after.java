class PlaceHold {
  public void removeRegions(OpenDefinitionsDocument doc) {
    assert doc != null;
    boolean found = _documents.remove(doc);
    if (found) {
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
      _regions.remove(doc);
    }
  }
}
