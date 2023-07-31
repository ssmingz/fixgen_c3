class PlaceHold {
  void drawGridLines(Event event, Enumeration drawColumns) {
    GC gc = event.gc;
    Color oldForeground = getForeground();
    Rectangle columnBounds;
    TableColumn column;
    int lineWidth = getGridLineWidth();
    int itemHeight = getItemHeight();
    int headerHeight = getHeaderHeight();
    int lineXPosition;
    int lineYPosition = headerHeight + (((event.y - headerHeight) / itemHeight) * itemHeight);
    int lineYStopPosition = event.y + event.height;
    gc.setForeground(display.getSystemColor(COLOR_WIDGET_LIGHT_SHADOW));
    if (itemHeight > 0) {
      while (lineYPosition < lineYStopPosition) {
        gc.drawLine(
            event.x,
            (lineYPosition + itemHeight) - lineWidth,
            event.x + event.width,
            (lineYPosition + itemHeight) - lineWidth);
        lineYPosition += itemHeight;
      }
    }
    while (drawColumns.hasMoreElements() == true) {
      column = ((TableColumn) (drawColumns.nextElement()));
      if (column.getIndex() != TableColumn.FILL) {
        columnBounds = column.getBounds();
        lineXPosition = (columnBounds.x + columnBounds.width) - lineWidth;
        gc.drawLine(lineXPosition, event.y, lineXPosition, event.y + event.height);
      }
    }
    gc.setForeground(oldForeground);
  }
}
