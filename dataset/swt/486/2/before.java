class PlaceHold {
  String getText(GC gc, TableColumn column) {
    int columnIndex = column.getIndex();
    String label = getTrimmedText(columnIndex);
    int maxWidth;
    if (label == null) {
      maxWidth = getMaxTextWidth(columnIndex, column.getWidth());
      label = getParent().trimItemText(getText(columnIndex), maxWidth, gc);
    }
    return label;
  }
}
