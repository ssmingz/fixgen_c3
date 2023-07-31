class PlaceHold {
  private void _updateBasedOnCurrentState() {
    ModelList<ReducedToken>.Iterator copyCursor = _cursor.copy();
    _updateBasedOnCurrentStateHelper(copyCursor);
    copyCursor.dispose();
  }
}
