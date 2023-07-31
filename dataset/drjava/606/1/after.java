class PlaceHold {
  public Vector<StateBlock> insertNewline() {
    if (_cursor.atStart()) {
      _insertNewEndOfLine();
    } else if (_cursor.atEnd()) {
      _insertNewEndOfLine();
    } else if ((_offset > 0) && _cursor.current().isMultipleCharBrace()) {
      _splitCurrentIfCommentBlock(true, _cursor);
      _cursor.next();
      _cursor.insert(Brace.MakeBrace("\n", getStateAtCurrent()));
      _cursor.prev();
      _updateBasedOnCurrentState();
      _cursor.next();
      _cursor.next();
      _offset = 0;
    } else if ((_offset > 0) && _cursor.current().isGap()) {
      _insertBraceToGap("\n", _cursor);
    } else {
      _insertNewEndOfLine();
    }
    return SBVectorFactory.generate(_cursor.copy(), _offset);
  }
}
