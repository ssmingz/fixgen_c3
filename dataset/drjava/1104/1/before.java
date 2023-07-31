class PlaceHold {
  public boolean canAbandonFile() {
    if (isModifiedSinceSave()
        || (((_file != null) && (!_file.exists())) && _cacheAdapter.isReady())) {
      return _notifier.canAbandonFile(this);
    } else {
      return true;
    }
  }
}
