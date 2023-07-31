class PlaceHold {
  private void _selectFoundOrReplacedItem(int length) {
    int offset = _machine.getCurrentOffset();
    int from;
    int to;
    if (_machine.isSearchBackwards()) {
      from = offset;
      to = offset + length;
    } else {
      from = offset - length;
      to = offset;
    }
    _selectFoundOrReplacedItem(from, to);
  }
}
