class PlaceHold {
  int menuForEvent(int id, int sel, int theEvent) {
    NSEvent event = new NSEvent(theEvent);
    NSTableView table = ((NSTableView) (view));
    NSIndexSet selectedRowIndexes = table.selectedRowIndexes();
    NSPoint mousePoint = view.convertPoint_fromView_(event.locationInWindow(), null);
    int row = table.rowAtPoint(mousePoint);
    if (selectedRowIndexes.containsIndex(row) == false) {
      table.selectRow(row, false);
    }
    return super.menuForEvent(id, sel, theEvent);
  }
}
