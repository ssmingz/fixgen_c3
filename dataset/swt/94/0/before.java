class PlaceHold {
  void headerDoMouseMove(Event event) {
    if (resizeColumn == null) {
      for (int i = 0; i < columns.length; i++) {
        TreeColumn column = columns[i];
        int x = column.getX() + column.width;
        if (Math.abs(x - event.x) <= TOLLERANCE_COLUMNRESIZE) {
          if (column.getResizable()) {
            setCursor(resizeCursor);
          } else {
            setCursor(null);
          }
          return;
        }
      }
      setCursor(null);
      return;
    }
    if (event.x <= resizeColumn.getX()) {
      return;
    }
    GC gc = new GC(this);
    int lineHeight = getClientArea().height;
    redraw(resizeColumnX - 1, 0, 1, lineHeight, false);
    resizeColumnX = event.x;
    gc.drawLine(resizeColumnX - 1, 0, resizeColumnX - 1, lineHeight);
    gc.dispose();
  }
}
