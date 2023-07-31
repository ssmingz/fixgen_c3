class PlaceHold {
  private void _insertNewEndOfLine() {
    _insertNewBrace("\n", _cursor);
    _cursor.prev();
    _updateBasedOnCurrentState();
    _cursor.next();
    _cursor.setBlockOffset(0);
  }
}
