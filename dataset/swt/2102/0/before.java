class PlaceHold {
  public void clear(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    if (!(((0 <= start) && (start <= end)) && (end < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if ((start == 0) && (end == (itemCount - 1))) {
      clearAll();
    } else {
      for (int i = start; i <= end; i++) {
        TableItem item = items[i];
        if (item != null) {
          item.clear();
        }
      }
      NSTableView widget = ((NSTableView) (view));
      widget.reloadData();
    }
  }
}
