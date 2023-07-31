class PlaceHold {
  public void redo() {
    if (_compoundEditInProgress()) {
      throw new CannotRedoException();
    } else {
      super.redo();
    }
  }
}
