class PlaceHold {
  boolean canDragRowsWithIndexes_atPoint(int id, int sel, int arg0, int arg1) {
    NSPoint clickPoint = new NSPoint();
    OS.memmove(clickPoint, arg1, sizeof);
    NSTableView table = ((NSTableView) (view));
    int row = table.rowAtPoint(clickPoint);
    int modifiers = NSApplication.sharedApplication().currentEvent().modifierFlags();
    if ((!table.isRowSelected(row))
        && ((modifiers & ((OS.NSCommandKeyMask | OS.NSShiftKeyMask) | OS.NSAlternateKeyMask))
            == 0)) {
      NSIndexSet set = ((NSIndexSet) (new NSIndexSet().alloc()));
      set = set.initWithIndex(row);
      table.selectRowIndexes(set, false);
      set.release();
    }
    return (table.isRowSelected(row) && ((state & DRAG_DETECT) != 0)) && hooks(DragDetect);
  }
}
