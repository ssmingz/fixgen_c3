class PlaceHold {
  void doPageUp() {
    int line = content.getLineAtOffset(caretOffset);
    if (line > 0) {
      int offsetInLine = caretOffset - content.getOffsetAtLine(line);
      int scrollLines = Math.max(1, Math.min(line, getLineCountWhole()));
      int scrollOffset;
      int caretX = getXAtOffset(content.getLine(line), line, offsetInLine);
      line -= scrollLines;
      if (isBidi()) {
        caretOffset = getBidiOffsetAtMouseLocation(caretX, line);
      } else {
        caretOffset = getOffsetAtMouseLocation(caretX, line);
      }
      scrollOffset = Math.max(0, verticalScrollOffset - (scrollLines * getVerticalIncrement()));
      if (scrollOffset < verticalScrollOffset) {
        setVerticalScrollOffset(scrollOffset, true);
      } else {
        showCaret();
      }
    }
  }
}
