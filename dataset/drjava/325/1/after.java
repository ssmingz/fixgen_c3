class PlaceHold {
  public void insertNewline() {
    if (_cursor.atStart()) {
      _insertNewEndOfLine();
    } else if (_cursor.atEnd()) {
      _insertNewEndOfLine();
    } else if ((_cursor.getBlockOffset() > 0) && _cursor.current().isMultipleCharBrace()) {
      _cursor._splitCurrentIfCommentBlock(true, true);
      _cursor.next();
      _cursor.insert(Brace.MakeBrace("\n", _getStateAtCurrent()));
      _cursor.prev();
      _updateBasedOnCurrentState();
      _cursor.next();
      _cursor.next();
      _cursor.setBlockOffset(0);
    } else if ((_cursor.getBlockOffset() > 0) && _cursor.current().isGap()) {
      _cursor.insertBraceToGap("\n");
      _cursor.prev();
      _cursor.prev();
      _updateBasedOnCurrentState();
      _cursor.next();
      _cursor.next();
    } else {
      _insertNewEndOfLine();
    }
    return;
  }
}
