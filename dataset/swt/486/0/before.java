class PlaceHold {
  void calculateSelectionExtent() {
    Table parent = getParent();
    TableColumn column = parent.internalGetColumn(FIRST);
    GC gc = new GC(parent);
    String trimmedText = getText(gc, column);
    int gridLineWidth = parent.getGridLineWidth();
    if (trimmedText != null) {
      selectionExtent = new Point(gc.stringExtent(trimmedText).x, parent.getItemHeight());
      selectionExtent.x += getTextIndent(FIRST) + SELECTION_PADDING;
      selectionExtent.x =
          Math.min(
              selectionExtent.x,
              (column.getWidth() - getImageStopX(column.getIndex())) - gridLineWidth);
      if (parent.getLinesVisible() == true) {
        selectionExtent.y -= gridLineWidth;
      }
    }
    gc.dispose();
  }
}
