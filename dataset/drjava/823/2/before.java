class PlaceHold {
  public Vector<StateBlock> insertBackSlash() {
    if (_braces.isEmpty()) {
      _insertNewBrace("\\", _cursor);
      return SBVectorFactory.generate(_cursor.copy(), _offset);
    }
    if (_cursor.atStart()) {
      _cursor.next();
    }
    if (_cursor.atEnd()) {
      _checkPreviousInsertBackSlash(_cursor);
      return SBVectorFactory.generate(_cursor.copy(), _offset);
    } else if ((_offset > 0) && _cursor.current().isMultipleCharBrace()) {
      _splitCurrentIfCommentBlock(true, true, _cursor);
      _cursor.next();
      _insertNewBrace("\\", _cursor);
      _cursor.prev();
      _cursor.prev();
      _updateBasedOnCurrentState();
      if (!_cursor.current().isMultipleCharBrace()) {
        _cursor.next();
      }
      _cursor.next();
    } else if ((_offset > 0) && _cursor.current().isGap()) {
      _insertBraceToGap("\\", _cursor);
    } else if ((_offset == 0) && _cursor.current().isMultipleCharBrace()) {
      _splitCurrentIfCommentBlock(false, true, _cursor);
      _checkPreviousInsertBackSlash(_cursor);
    } else {
      _checkPreviousInsertBackSlash(_cursor);
    }
    return SBVectorFactory.generate(_cursor.copy(), _offset);
  }
}
