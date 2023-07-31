class PlaceHold {
  boolean canDragRowsWithIndexes_atPoint(int id, int sel, int arg0, int arg1) {
    NSPoint clickPoint = new NSPoint();
    OS.memmove(clickPoint, arg1, sizeof);
    NSTableView table = ((NSTableView) (view));
    int row = table.rowAtPoint(clickPoint);
    if (!table.isRowSelected(row)) {
      NSIndexSet set = ((NSIndexSet) (new NSIndexSet().alloc()));
      set = set.initWithIndex(row);
      table.selectRowIndexes(set, false);
      set.release();
    }
    return ((state & DRAG_DETECT) != 0) && hooks(DragDetect);
  }
}
