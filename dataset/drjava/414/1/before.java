class PlaceHold {
  public void undo() {
    if (_compoundEditInProgress()) {
      throw new CannotUndoException();
    } else {
      super.undo();
    }
  }
}
