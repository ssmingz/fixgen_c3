class PlaceHold {
  private void _checkPreviousInsertCommentChar(String special) {
    if ((!_cursor.atStart()) && (!_cursor.atFirstItem())) {
      if (_cursor.prevItem().getType().equals("/") && (_cursor.prevItem().getState() == FREE)) {
        _cursor.prevItem().setType("/" + special);
        _updateBasedOnCurrentState();
        return;
      } else if ((_cursor.prevItem().getType().equals("*")
              && (getStateAtCurrent() == INSIDE_BLOCK_COMMENT))
          && special.equals("/")) {
        _cursor.prevItem().setType("*" + special);
        _cursor.prevItem().setState(FREE);
        _updateBasedOnCurrentState();
        return;
      }
    }
    _insertNewBrace(special, _cursor);
    _cursor.prev();
    _updateBasedOnCurrentState();
    if (_cursor.current().getSize() == 2) {
      _cursor.setBlockOffset(1);
    } else {
      _cursor.next();
    }
  }
}
