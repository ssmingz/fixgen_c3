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
    TableColumn column = parent.columns[columnIndex];
    int availableWidth = (column.width - (2 * parent.getCellPadding())) - (2 * MARGIN_TEXT);
    if (columnIndex == 0) {
      availableWidth -= parent.col0ImageWidth;
      if (parent.col0ImageWidth > 0) {
        availableWidth -= Table.MARGIN_IMAGE;
      }
      if ((parent.style & SWT.CHECK) != 0) {
        availableWidth -= parent.checkboxBounds.width;
        availableWidth -= Table.MARGIN_IMAGE;
      }
    } else {
      Image image = getImage(columnIndex, false);
      if (image != null) {
        availableWidth -= image.getBounds().width;
        availableWidth -= Table.MARGIN_IMAGE;
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
      displayTexts[columnIndex] = Table.ELLIPSIS;
      textWidths[columnIndex] = ellipsisWidth;
      return;
    }
    int index = Math.min(availableWidth / gc.getFontMetrics().getAverageCharWidth(), text.length());
    textWidth = gc.stringExtent(text.substring(0, index)).x;
    if (availableWidth == textWidth) {
      displayTexts[columnIndex] = text.substring(0, index) + Table.ELLIPSIS;
      textWidths[columnIndex] = textWidth + ellipsisWidth;
      return;
    }
    if (availableWidth < textWidth) {
      do {
        index--;
        if (index < 0) {
          displayTexts[columnIndex] = Table.ELLIPSIS;
          textWidths[columnIndex] = ellipsisWidth;
          return;
        }
        text = text.substring(0, index);
        textWidth = gc.stringExtent(text).x;
      } while (availableWidth < textWidth);
      displayTexts[columnIndex] = text + Table.ELLIPSIS;
      textWidths[columnIndex] = textWidth + ellipsisWidth;
      return;
    }
    int previousWidth = 0;
    while (textWidth < availableWidth) {
      index++;
      previousWidth = textWidth;
      textWidth = gc.stringExtent(text.substring(0, index)).x;
    }
    displayTexts[columnIndex] = text.substring(0, index - 1) + Table.ELLIPSIS;
    textWidths[columnIndex] = previousWidth + ellipsisWidth;
  }
}
