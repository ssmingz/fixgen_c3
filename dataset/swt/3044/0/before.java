class PlaceHold {
  void tableViewSelectionDidChange(int id, int sel, int aNotification) {
    if (ignoreSelect) {
      return;
    }
    NSTableView widget = ((NSTableView) (view));
    int row = ((int) (widget.selectedRow()));
    if (row == (-1)) {
      postEvent(Selection);
    } else {
      TableItem item = _getItem(row);
      Event event = new Event();
      event.item = item;
      event.index = row;
      postEvent(Selection, event);
    }
  }
}
