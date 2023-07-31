class PlaceHold {
  boolean scrollCaret() {
    int line = content.getLineAtOffset(caretOffset);
    int lineOffset = content.getOffsetAtLine(line);
    int offsetInLine = caretOffset - lineOffset;
    String lineText = content.getLine(line);
    int xAtOffset = getXAtOffset(lineText, line, offsetInLine);
    int clientAreaWidth = getClientArea().width;
    int verticalIncrement = getVerticalIncrement();
    int horizontalIncrement = clientAreaWidth / 4;
    boolean scrolled = false;
    if (xAtOffset < 0) {
      xAtOffset = Math.max(horizontalScrollOffset * (-1), xAtOffset - horizontalIncrement);
      scrollHorizontalBar(xAtOffset);
      scrolled = true;
    } else if (xAtOffset > clientAreaWidth) {
      xAtOffset = Math.min(contentWidth - horizontalScrollOffset, xAtOffset + horizontalIncrement);
      scrollHorizontalBar(xAtOffset - clientAreaWidth);
      scrolled = true;
    }
    if (line < topIndex) {
      setVerticalScrollOffset(line * verticalIncrement, true);
      scrolled = true;
    } else if (line > getBottomIndex()) {
      setVerticalScrollOffset(
          ((line - getBottomIndex()) * verticalIncrement) + verticalScrollOffset, true);
      scrolled = true;
    }
    return scrolled;
  }
}
