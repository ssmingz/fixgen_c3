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
    headerPaintShadow(gc, clipping.x, 0, clipping.width, header.getSize().y, true, false);
    if (startColumn == (-1)) {
      return;
    }
    if (numColumns == 0) {
      return;
    }
    int height = getClientArea().height;
    for (int i = startColumn; i <= endColumn; i++) {
      headerPaintShadow(gc, columns[i].getX(), 0, columns[i].width, height, false, true);
      columns[i].paint(gc);
    }
  }
}
