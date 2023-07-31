class PlaceHold {
  public Vector<StateBlock> insertQuote() {
    if (_cursor.atStart()) {
      _insertNewQuote();
    } else if (_cursor.atEnd()) {
      _insertNewQuote();
    } else if ((_offset > 0) && _cursor.current().isMultipleCharBrace()) {
      _splitCurrentIfCommentBlock(true, _cursor);
      _cursor.next();
      _cursor.insert(Brace.MakeBrace("\"", getStateAtCurrent()));
      _cursor.prev();
      _updateBasedOnCurrentState();
      _cursor.next();
      _cursor.next();
      _offset = 0;
    } else if ((_offset > 0) && _cursor.current().isGap()) {
      _insertBraceToGap("\"", _cursor);
    } else {
      _insertNewQuote();
    }
    return SBVectorFactory.generate(_cursor.copy(), _offset);
  }
}
