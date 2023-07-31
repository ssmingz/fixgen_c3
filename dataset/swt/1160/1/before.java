class PlaceHold {
  void headerOnPaint(Event event) {
    TableColumn[] orderedColumns = getOrderedColumns();
    int numColumns = orderedColumns.length;
    GC gc = event.gc;
    Rectangle clipping = gc.getClipping();
    int startColumn = -1;
    int endColumn = -1;
    if (numColumns > 0) {
      startColumn = computeColumnIntersect(clipping.x, 0);
      if (startColumn != (-1)) {
        endColumn = computeColumnIntersect(clipping.x + clipping.width, startColumn);
        if (endColumn == (-1)) {
          endColumn = numColumns - 1;
        }
      }
    } else {
      startColumn = endColumn = 0;
    }
    Rectangle paintBounds = new Rectangle(clipping.x, 0, clipping.width, header.getSize().y);
    headerPaintShadow(gc, paintBounds, true, false);
    if (startColumn == (-1)) {
      return;
    }
    if (numColumns == 0) {
      return;
    }
    for (int i = startColumn; i <= endColumn; i++) {
      Rectangle bounds =
          new Rectangle(
              orderedColumns[i].getX(), 0, orderedColumns[i].width, getClientArea().height);
      headerPaintShadow(gc, bounds, false, true);
      orderedColumns[i].paint(gc);
    }
  }
}
