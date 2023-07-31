class PlaceHold {
  public void insertSlash() {
    if (_braces.isEmpty()) {
      _insertNewBrace("/", _cursor);
      return;
    }
    if (_cursor.atStart()) {
      _cursor.next();
    }
    if (_cursor.atEnd()) {
      _checkPreviousInsertSlash(_cursor);
      return;
    } else if ((_offset > 0) && _cursor.current().isMultipleCharBrace()) {
      _splitCurrentIfCommentBlock(true, true, _cursor);
      _cursor.next();
      _insertNewBrace("/", _cursor);
      _cursor.prev();
      _cursor.prev();
      _updateBasedOnCurrentState();
      if (!_cursor.current().isMultipleCharBrace()) {
        _cursor.next();
      }
      _cursor.next();
    } else if ((_offset > 0) && _cursor.current().isGap()) {
      _insertBraceToGap("/", _cursor);
    } else if ((_offset == 0) && _cursor.current().isMultipleCharBrace()) {
      _splitCurrentIfCommentBlock(false, false, _cursor);
      _checkPreviousInsertSlash(_cursor);
    } else {
      _checkPreviousInsertSlash(_cursor);
    }
    return;
  }
}
