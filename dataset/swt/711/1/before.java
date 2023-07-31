class PlaceHold {
  int getContentX(int columnIndex) {
    if (columnIndex > 0) {
      TreeColumn column = parent.columns[columnIndex];
      int contentX = column.getX() + parent.getCellPadding();
      if ((column.style & SWT.LEFT) != 0) {
        return contentX;
      }
      int contentWidth = getContentWidth(columnIndex);
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
    int contentX = parent.getCellPadding() - parent.horizontalOffset;
    if (parentItem != null) {
      int expanderWidth = parent.expanderBounds.width + INDENT_HIERARCHY;
      contentX += expanderWidth * depth;
    }
    contentX += parent.expanderBounds.width;
    return (contentX + Tree.MARGIN_IMAGE) + INDENT_HIERARCHY;
  }
}
