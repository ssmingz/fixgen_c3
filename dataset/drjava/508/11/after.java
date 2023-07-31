class PlaceHold {
  private void _updateBasedOnCurrentState() {
    TokenList.Iterator copyCursor = _cursor.copy();
    _updateBasedOnCurrentStateHelper(copyCursor);
    copyCursor.dispose();
  }
}
