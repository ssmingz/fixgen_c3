class PlaceHold {
  Point getItemExtent(TableColumn column) {
    Table parent = getParent();
    int columnIndex = column.getIndex();
    Point extent =
        new Point(getImageStopX(columnIndex), parent.getItemHeight() - parent.getGridLineWidth());
    GC gc = new GC(parent);
    gc.setFont(getFont());
    String trimmedText = getText(gc, column);
    if ((trimmedText != null) && (trimmedText.length() > 0)) {
      extent.x += gc.stringExtent(trimmedText).x + getTextIndent(columnIndex);
    }
    if (columnIndex == TableColumn.FIRST) {
      extent.x += SELECTION_PADDING;
    }
    gc.dispose();
    return extent;
  }
}
