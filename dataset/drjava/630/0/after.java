class PlaceHold {
  public void resetUndoManager() {
    _undoManager = new CompoundUndoManager(_notifier);
    _undoManager.setLimit(UNDO_LIMIT);
  }
}
