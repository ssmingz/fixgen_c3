class PlaceHold {
  public void endCompoundEdit(int key) {
    if (_keys.elementAt(0).intValue() == key) {
      CompoundEdit compoundEdit = _compoundEdits.elementAt(0);
      _compoundEdits.removeElementAt(0);
      compoundEdit.end();
      if (!_compoundEditInProgress()) {
        super.addEdit(compoundEdit);
      } else {
        _compoundEdits.firstElement().addEdit(compoundEdit);
      }
      _keys.removeElementAt(0);
    } else {
      throw new IllegalStateException("Improperly nested compound edits.");
    }
  }
}
