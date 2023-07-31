class PlaceHold {
  public void resetUndoManager() {
    _undoManager = new CompoundUndoManager();
    _undoManager.setLimit(UNDO_LIMIT);
  }
}
