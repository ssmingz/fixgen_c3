class PlaceHold {
  public void redo() {
    if (_compoundEditInProgress()) {
      while (_keys.size() > 0) {
        endCompoundEdit(_keys.get(0).intValue());
      }
      super.redo();
    } else {
      super.redo();
    }
  }
}
