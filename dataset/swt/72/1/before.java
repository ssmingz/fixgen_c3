class PlaceHold {
  boolean canDragRowsWithIndexes_atPoint(int id, int sel, int arg0, int arg1) {
    NSPoint clickPoint = new NSPoint();
    OS.memmove(clickPoint, arg1, sizeof);
    NSOutlineView tree = ((NSOutlineView) (view));
    int row = tree.rowAtPoint(clickPoint);
    if (!tree.isRowSelected(row)) {
      NSIndexSet set = ((NSIndexSet) (new NSIndexSet().alloc()));
      set = set.initWithIndex(row);
      tree.selectRowIndexes(set, false);
      set.release();
    }
    return ((state & DRAG_DETECT) != 0) && hooks(DragDetect);
  }
}
