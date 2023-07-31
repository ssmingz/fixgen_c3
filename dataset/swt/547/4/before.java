class PlaceHold {
  public void select(int index) {
    checkWidget();
    if ((0 <= index) && (index < itemCount)) {
      NSIndexSet indexes = ((NSIndexSet) (new NSIndexSet().alloc()));
      indexes.initWithIndex(index);
      NSTableView widget = ((NSTableView) (view));
      ignoreSelect = true;
      widget.selectRowIndexes(indexes, true);
      ignoreSelect = false;
    }
  }
}
