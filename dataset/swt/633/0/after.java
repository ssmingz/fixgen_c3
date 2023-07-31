class PlaceHold {
  int getContentX(int columnIndex) {
    if (columnIndex > 0) {
      TreeColumn column = parent.columns[columnIndex];
      int contentX = column.getX() + MARGIN_TEXT;
      if ((column.style & SWT.LEFT) != 0) {
        return contentX;
      }
      int contentWidth = textWidths[columnIndex];
      Image image = images[columnIndex];
      if (image != null) {
        contentWidth += Tree.MARGIN_IMAGE + image.getBounds().width;
      }
      if ((column.style & SWT.RIGHT) != 0) {
        int padding = parent.getCellPadding();
        contentX = Math.max(contentX, ((column.getX() + column.width) - padding) - contentWidth);
      } else {
        contentX = Math.max(contentX, column.getX() + ((column.width - contentWidth) / 2));
      }
      return contentX;
    }
    if ((parent.style & SWT.CHECK) != 0) {
      Rectangle checkBounds = getCheckboxBounds();
      return (checkBounds.x + checkBounds.width) + Tree.MARGIN_IMAGE;
    }
    int x = parent.getCellPadding() - parent.horizontalOffset;
    if (parentItem != null) {
      int expanderWidth = parent.expanderBounds.width + INDENT_HIERARCHY;
      x += expanderWidth * depth;
    }
    x += parent.expanderBounds.width;
    if (items.length == 0) {
      x += Compatibility.floor(parent.expanderBounds.width, 2);
    }
    return (x + Tree.MARGIN_IMAGE) + INDENT_HIERARCHY;
  }
}
