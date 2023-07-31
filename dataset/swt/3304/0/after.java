class PlaceHold {
  void showOffset(int offset) {
    int line = content.getLineAtOffset(offset);
    int lineOffset = content.getOffsetAtLine(line);
    int offsetInLine = offset - lineOffset;
    String lineText = content.getLine(line);
    int xAtOffset = getXAtOffset(lineText, line, offsetInLine);
    int clientAreaWidth = getClientArea().width;
    int verticalIncrement = getVerticalIncrement();
    int horizontalIncrement = clientAreaWidth / 4;
    if (xAtOffset < 0) {
      xAtOffset = Math.max(horizontalScrollOffset * (-1), xAtOffset - horizontalIncrement);
      scrollHorizontalBar(xAtOffset);
    } else if (xAtOffset > clientAreaWidth) {
      xAtOffset =
          Math.min(
              contentWidth.getWidth() - horizontalScrollOffset, xAtOffset + horizontalIncrement);
      scrollHorizontalBar(xAtOffset - clientAreaWidth);
    }
    if (line < topIndex) {
      setVerticalScrollOffset(line * verticalIncrement, true);
    } else if (line > getBottomIndex()) {
      setVerticalScrollOffset(
          ((line - getBottomIndex()) * verticalIncrement) + verticalScrollOffset, true);
    }
  }
}
