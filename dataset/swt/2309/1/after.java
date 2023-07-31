class PlaceHold {
  String headerGetToolTip(int x) {
    if (resizeColumn != null) {
      return null;
    }
    int orderedIndex = computeColumnIntersect(x, 0);
    if (orderedIndex == (-1)) {
      return null;
    }
    TableColumn[] orderedColumns = getOrderedColumns();
    TableColumn column = orderedColumns[orderedIndex];
    if (column.toolTipText == null) {
      return null;
    }
    int columnX = column.getX();
    if ((orderedIndex > 0) && orderedColumns[orderedIndex - 1].resizable) {
      if ((x - columnX) <= TOLLERANCE_COLUMNRESIZE) {
        return null;
      }
    }
    if (column.resizable) {
      int columnRightX = columnX + column.width;
      if ((columnRightX - x) <= TOLLERANCE_COLUMNRESIZE) {
        return null;
      }
    }
    return column.toolTipText;
  }
}
