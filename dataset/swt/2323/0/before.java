class PlaceHold {
  int menuForEvent(int id, int sel, int theEvent) {
    NSEvent event = new NSEvent(theEvent);
    NSOutlineView tree = ((NSOutlineView) (view));
    NSIndexSet selectedRowIndexes = tree.selectedRowIndexes();
    NSPoint mousePoint = view.convertPoint_fromView_(event.locationInWindow(), null);
    int row = tree.rowAtPoint(mousePoint);
    if (selectedRowIndexes.containsIndex(row) == false) {
      NSIndexSet indexes = new NSIndexSet(NSIndexSet.indexSetWithIndex(row));
      tree.selectRowIndexes(indexes, false);
    }
    return super.menuForEvent(id, sel, theEvent);
  }
}
