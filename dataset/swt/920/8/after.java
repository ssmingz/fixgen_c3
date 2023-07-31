class PlaceHold {
  public void deselect(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    if ((end < 0) || (start >= itemCount)) {
      return;
    }
    start = Math.max(0, start);
    end = Math.min(itemCount - 1, end);
    int length = (end - start) + 1;
    if (length <= 0) {
      return;
    }
    if ((start == 0) && (end == (itemCount - 1))) {
      deselectAll();
    } else {
      NSTableView widget = ((NSTableView) (view));
      ignoreSelect = true;
      for (int i = 0; i < length; i++) {
        widget.deselectRow(i);
      }
      ignoreSelect = false;
    }
  }
}
