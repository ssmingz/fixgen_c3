class PlaceHold {
  public void insertGap(int length) {
    if (_cursor.atStart()) {
      if (_gapToRight()) {
        _cursor.next();
        _augmentCurrentGap(length);
      } else {
        _insertNewGap(length);
      }
    } else if (_cursor.atEnd()) {
      if (_gapToLeft()) {
        _augmentGapToLeft(length);
      } else {
        _insertNewGap(length);
      }
    } else if (_cursor.current().isMultipleCharBrace() && (_offset > 0)) {
      if (_offset > 1) {
        throw new IllegalArgumentException("OFFSET TOO BIG:  " + _offset);
      }
      _breakComment(_cursor);
      _insertNewGap(length);
    } else if (_cursor.current().isGap()) {
      _cursor.current().grow(length);
      _offset += length;
    } else if ((!_cursor.atFirstItem()) && _cursor.prevItem().isGap()) {
      _cursor.prevItem().grow(length);
    } else {
      _insertNewGap(length);
    }
  }
}
