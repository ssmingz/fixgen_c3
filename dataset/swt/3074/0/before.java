class PlaceHold {
  public void select(int start, int end) {
    checkWidget();
    if (((end < 0) || (start > end)) || (((style & SWT.SINGLE) != 0) && (start != end))) {
      return;
    }
    if ((itemCount == 0) || (start >= itemCount)) {
      return;
    }
    if ((start == 0) && (end == (itemCount - 1))) {
      selectAll();
    } else {
      start = Math.max(0, start);
      end = Math.min(end, itemCount - 1);
      int length = (end - start) + 1;
      NSIndexSet indexes = ((NSIndexSet) (new NSIndexSet().alloc()));
      NSRange range = new NSRange();
      range.location = start;
      range.length = length;
      indexes.initWithIndexesInRange(range);
      NSTableView widget = ((NSTableView) (view));
      widget.setDelegate(null);
      widget.selectRowIndexes(indexes, true);
      widget.setDelegate(widget);
    }
  }
}
