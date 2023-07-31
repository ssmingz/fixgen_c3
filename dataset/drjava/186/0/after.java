class PlaceHold {
  public void undo() {
    if (_compoundEditInProgress()) {
      while (_keys.size() > 0) {
        endCompoundEdit(_keys.get(0).intValue());
      }
      super.undo();
    } else {
      super.undo();
    }
  }
}
