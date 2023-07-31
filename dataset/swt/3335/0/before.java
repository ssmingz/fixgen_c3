class PlaceHold {
  boolean dragDetect(int x, int y, boolean filter, boolean[] consume) {
    boolean dragging = super.dragDetect(x, y, filter, consume);
    if (dragging) {
      NSTableView widget = ((NSTableView) (view));
      NSPoint pt = new NSPoint();
      pt.x = x;
      pt.y = y;
      int row = ((int) (widget.rowAtPoint(pt)));
      if (!widget.isRowSelected(row)) {
        widget.selectRow(row, false);
      }
    }
    consume[0] = dragging;
    return dragging;
  }
}
