class PlaceHold {
  boolean canDragRowsWithIndexes_atPoint(int id, int sel, int arg0, int arg1) {
    NSPoint clickPoint = new NSPoint();
    OS.memmove(clickPoint, arg1, sizeof);
    NSOutlineView tree = ((NSOutlineView) (view));
    int row = tree.rowAtPoint(clickPoint);
    int modifiers = NSApplication.sharedApplication().currentEvent().modifierFlags();
    boolean drag = ((state & DRAG_DETECT) != 0) && hooks(DragDetect);
    if (drag) {
      if ((!tree.isRowSelected(row))
          && ((modifiers
                  & (((OS.NSCommandKeyMask | OS.NSShiftKeyMask) | OS.NSAlternateKeyMask)
                      | OS.NSControlKeyMask))
              == 0)) {
        NSIndexSet set = ((NSIndexSet) (new NSIndexSet().alloc()));
        set = set.initWithIndex(row);
        tree.selectRowIndexes(set, false);
        set.release();
      }
    }
    return tree.isRowSelected(row) && drag;
  }
}
