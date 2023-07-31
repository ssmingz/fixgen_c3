class PlaceHold {
  public boolean addEdit(UndoableEdit e) {
    if (!_compoundEdits.isEmpty()) {
      return _compoundEdits.firstElement().addEdit(e);
    } else {
      return super.addEdit(e);
    }
  }
}
