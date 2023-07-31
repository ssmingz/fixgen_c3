class PlaceHold {
  public void resetUndoManager() {
    if (_cache.isDDocInCache(this)) {
      getDocument().resetUndoManager();
    }
  }
}
