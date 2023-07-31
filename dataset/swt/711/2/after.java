class PlaceHold {
  void computeDisplayText(int columnIndex, GC gc) {
    if (((parent.style & SWT.VIRTUAL) != 0) && (!cached)) {
      return;
    }
    int columnCount = parent.columns.length;
    if (columnCount == 0) {
      String text = getText(0, false);
      textWidths[columnIndex] = gc.stringExtent(text).x;
      return;
    }
    int orderedIndex =
        (parent.columns.length == 0) ? 0 : parent.columns[columnIndex].getOrderIndex();
    TreeColumn column = parent.columns[columnIndex];
    int availableWidth;
    if (orderedIndex == 0) {
      availableWidth = ((column.getX() + column.width) - getTextX(columnIndex)) - (2 * MARGIN_TEXT);
    } else {
      availableWidth = (column.width - (2 * parent.getCellPadding())) - (2 * MARGIN_TEXT);
      if (images[columnIndex] != null) {
        availableWidth -= images[columnIndex].getBounds().width;
        availableWidth -= Tree.MARGIN_IMAGE;
      }
    }
    String text = getText(columnIndex, false);
    int textWidth = gc.stringExtent(text).x;
    if (textWidth <= availableWidth) {
      displayTexts[columnIndex] = text;
      textWidths[columnIndex] = textWidth;
      return;
    }
    int ellipsisWidth = gc.stringExtent(ELLIPSIS).x;
    availableWidth -= ellipsisWidth;
    if (availableWidth <= 0) {
      displayTexts[columnIndex] = Tree.ELLIPSIS;
      textWidths[columnIndex] = ellipsisWidth;
      return;
    }
    int index = Math.min(availableWidth / gc.getFontMetrics().getAverageCharWidth(), text.length());
    textWidth = gc.stringExtent(text.substring(0, index)).x;
    if (availableWidth == textWidth) {
      displayTexts[columnIndex] = text.substring(0, index) + Tree.ELLIPSIS;
      textWidths[columnIndex] = textWidth + ellipsisWidth;
      return;
    }
    if (availableWidth < textWidth) {
      do {
        index--;
        if (index < 0) {
          displayTexts[columnIndex] = Tree.ELLIPSIS;
          textWidths[columnIndex] = ellipsisWidth;
          return;
        }
        text = text.substring(0, index);
        textWidth = gc.stringExtent(text).x;
      } while (availableWidth < textWidth);
      displayTexts[columnIndex] = text + Tree.ELLIPSIS;
      textWidths[columnIndex] = textWidth + ellipsisWidth;
      return;
    }
    int previousWidth = 0;
    while (textWidth < availableWidth) {
      index++;
      previousWidth = textWidth;
      textWidth = gc.stringExtent(text.substring(0, index)).x;
    }
    displayTexts[columnIndex] = text.substring(0, index - 1) + Tree.ELLIPSIS;
    textWidths[columnIndex] = previousWidth + ellipsisWidth;
  }
}
