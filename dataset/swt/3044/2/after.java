class PlaceHold {
  void outlineViewSelectionDidChange(int id, int sel, int notification) {
    if (ignoreSelect) {
      return;
    }
    NSOutlineView widget = ((NSOutlineView) (view));
    int row = ((int) (widget.selectedRow()));
    if (row == (-1)) {
      sendSelectionEvent(Selection);
    } else {
      id _id = widget.itemAtRow(row);
      TreeItem item = ((TreeItem) (display.getWidget(_id.id)));
      Event event = new Event();
      event.item = item;
      event.index = row;
      sendSelectionEvent(Selection, event, false);
    }
  }
}
