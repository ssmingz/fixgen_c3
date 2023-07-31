class PlaceHold {
  public boolean addEdit(UndoableEdit e) {
    if (_compoundEditInProgress()) {
      return _compoundEdits.firstElement().addEdit(e);
    } else {
      return super.addEdit(e);
    }
  }
}
