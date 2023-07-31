class PlaceHold {
  public synchronized int startCompoundEdit() {
    _compoundEdits.add(0, new CompoundEdit());
    _keys.add(0, new Integer(_nextKey));
    if (_nextKey < Integer.MAX_VALUE) {
      _nextKey++;
    } else {
      _nextKey = Integer.MIN_VALUE;
    }
    return _keys.get(0).intValue();
  }
}
