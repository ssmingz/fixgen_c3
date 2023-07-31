class PlaceHold {
  private void _checkPreviousInsertBackSlash() {
    if ((!_cursor.atStart()) && (!_cursor.atFirstItem())) {
      if (_cursor.prevItem().getType().equals("\\")) {
        _cursor.prevItem().setType("\\\\");
        _updateBasedOnCurrentState();
        return;
      }
    }
    _cursor.insertNewBrace("\\");
    _cursor.prev();
    _updateBasedOnCurrentState();
    if (_cursor.current().getSize() == 2) {
      _cursor.setBlockOffset(1);
    } else {
      _cursor.next();
    }
  }
}
