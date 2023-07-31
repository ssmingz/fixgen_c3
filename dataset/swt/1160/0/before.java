class PlaceHold {
  void headerOnPaint(Event event) {
    int numColumns = columns.length;
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
          new Rectangle(columns[i].getX(), 0, columns[i].width, getClientArea().height);
      headerPaintShadow(gc, bounds, false, true);
      columns[i].paint(gc);
    }
  }
}
