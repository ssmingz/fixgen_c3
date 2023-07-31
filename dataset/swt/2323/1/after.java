class PlaceHold {
  int menuForEvent(int id, int sel, int theEvent) {
    if (id != headerView.id) {
      NSEvent event = new NSEvent(theEvent);
      NSTableView table = ((NSTableView) (view));
      NSIndexSet selectedRowIndexes = table.selectedRowIndexes();
      NSPoint mousePoint = view.convertPoint_fromView_(event.locationInWindow(), null);
      int row = table.rowAtPoint(mousePoint);
      if (selectedRowIndexes.containsIndex(row) == false) {
        NSIndexSet indexes = new NSIndexSet(NSIndexSet.indexSetWithIndex(row));
        table.selectRowIndexes(indexes, false);
      }
    }
    return super.menuForEvent(id, sel, theEvent);
  }
}
