class PlaceHold {
  void doPageDown(boolean select) {
    int line = content.getLineAtOffset(caretOffset);
    int lineCount = content.getLineCount();
    if (line < (lineCount - 1)) {
      int offsetInLine = caretOffset - content.getOffsetAtLine(line);
      int verticalMaximum = content.getLineCount() * getVerticalIncrement();
      int pageSize = getClientArea().height;
      int scrollLines = Math.min((lineCount - line) - 1, getLineCountWhole());
      int scrollOffset;
      int caretX = getXAtOffset(content.getLine(line), line, offsetInLine);
      scrollLines = Math.max(1, scrollLines);
      line += scrollLines;
      if (isBidi()) {
        caretOffset = getBidiOffsetAtMouseLocation(caretX, line);
      } else {
        caretOffset = getOffsetAtMouseLocation(caretX, line);
      }
      if (select) {
        doSelection(RIGHT);
      }
      scrollOffset = verticalScrollOffset + (scrollLines * getVerticalIncrement());
      if ((scrollOffset + pageSize) > verticalMaximum) {
        scrollOffset = verticalMaximum - pageSize;
      }
      if (scrollOffset > verticalScrollOffset) {
        setVerticalScrollOffset(scrollOffset, true);
      } else {
        showCaret();
      }
    }
  }
}
