class PlaceHold {
  public TreeItem getItem(Point point) {
    checkWidget();
    if (point == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    NSOutlineView widget = ((NSOutlineView) (view));
    NSPoint pt = new NSPoint();
    pt.x = point.x;
    pt.y = point.y;
    int row = ((int) (widget.rowAtPoint(pt)));
    if (row == (-1)) {
      return null;
    }
    id id = widget.itemAtRow(row);
    Widget item = display.getWidget(id.id);
    if ((item != null) && (item instanceof TreeItem)) {
      return ((TreeItem) (item));
    }
    return null;
  }
}
