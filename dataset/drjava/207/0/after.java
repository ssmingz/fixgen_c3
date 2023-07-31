class PlaceHold {
  public boolean isModifiedSinceSave() {
    if (_cache.isDDocInCache(this)) {
      return getDocument().isModifiedSinceSave();
    } else {
      return false;
    }
  }
}
