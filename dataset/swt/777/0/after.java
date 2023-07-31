class PlaceHold {
  int getContentX(int columnIndex) {
    if (columnIndex > 0) {
      TreeColumn column = parent.getColumn(columnIndex);
      int contentX = column.getX() + MARGIN_TEXT;
      if ((column.style & SWT.LEFT) != 0) {
        return contentX;
      }
      int contentWidth = getTextWidth(columnIndex);
      Image image = internalGetImage(columnIndex);
      if (image != null) {
        contentWidth += Tree.MARGIN_IMAGE + image.getBounds().width;
      }
      if ((column.style & SWT.RIGHT) != 0) {
        int padding = parent.getCellPadding();
        contentX =
            Math.max(contentX, ((column.getX() + column.getWidth()) - padding) - contentWidth);
      } else {
        contentX = Math.max(contentX, column.getX() + ((column.getWidth() - contentWidth) / 2));
      }
      return contentX;
    }
    if ((parent.style & SWT.CHECK) != 0) {
      Rectangle checkBounds = getCheckboxBounds();
      return (checkBounds.x + checkBounds.width) + Tree.MARGIN_IMAGE;
    }
    return getHconnectorEndpoints()[1].x + Tree.MARGIN_IMAGE;
  }
}
