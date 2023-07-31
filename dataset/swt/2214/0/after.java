class PlaceHold {
  void computeDisplayText(GC gc) {
    int availableWidth = width - (2 * parent.getHeaderPadding());
    if (image != null) {
      availableWidth -= image.getBounds().width;
      availableWidth -= Tree.MARGIN_IMAGE;
    }
    if (sort != SWT.NONE) {
      availableWidth -= parent.arrowBounds.width;
      availableWidth -= Tree.MARGIN_IMAGE;
    }
    String text = this.text;
    int textWidth = gc.textExtent(text, DRAW_MNEMONIC).x;
    if (textWidth <= availableWidth) {
      displayText = text;
      return;
    }
    int ellipsisWidth = gc.stringExtent(ELLIPSIS).x;
    availableWidth -= ellipsisWidth;
    if (availableWidth <= 0) {
      displayText = Tree.ELLIPSIS;
      return;
    }
    int index = availableWidth / gc.getFontMetrics().getAverageCharWidth();
    textWidth = gc.textExtent(text.substring(0, index), DRAW_MNEMONIC).x;
    if (availableWidth == textWidth) {
      displayText = text.substring(0, index) + Tree.ELLIPSIS;
      return;
    }
    if (availableWidth < textWidth) {
      do {
        index--;
        if (index < 0) {
          displayText = Tree.ELLIPSIS;
          return;
        }
        text = text.substring(0, index);
        textWidth = gc.textExtent(text, DRAW_MNEMONIC).x;
      } while (availableWidth < textWidth);
      displayText = text + Tree.ELLIPSIS;
      return;
    }
    while (textWidth < availableWidth) {
      index++;
      textWidth = gc.textExtent(text.substring(0, index), DRAW_MNEMONIC).x;
    }
    displayText = text.substring(0, index - 1) + Tree.ELLIPSIS;
  }
}
