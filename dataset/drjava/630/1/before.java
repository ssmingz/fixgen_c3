class PlaceHold {
  public void resetUndoManager() {
    _undoManager = new OurUndoManager();
    _undoManager.setLimit(UNDO_LIMIT);
  }
}
