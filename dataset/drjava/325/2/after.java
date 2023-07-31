class PlaceHold {
  public void insertQuote(String quote) {
    if (_cursor.atStart()) {
      _insertNewQuote(quote);
    } else if (_cursor.atEnd()) {
      _insertNewQuote(quote);
    } else if ((_cursor.getBlockOffset() > 0) && _cursor.current().isMultipleCharBrace()) {
      _cursor._splitCurrentIfCommentBlock(true, true);
      _cursor.next();
      _cursor.insert(Brace.MakeBrace(quote, _getStateAtCurrent()));
      _cursor.prev();
      _updateBasedOnCurrentState();
      if (!_cursor.current().isMultipleCharBrace()) {
        _cursor.next();
      }
      _cursor.next();
      _cursor.setBlockOffset(0);
    } else if ((_cursor.getBlockOffset() > 0) && _cursor.current().isGap()) {
      _cursor.insertBraceToGap(quote);
      _cursor.prev();
      _cursor.prev();
      _updateBasedOnCurrentState();
      _cursor.next();
      _cursor.next();
    } else {
      _insertNewQuote(quote);
    }
    return;
  }
}
