class PlaceHold {
  void doPageUp() {
    int oldColumnX = columnX;
    int caretLine = getCaretLine();
    if (caretLine > 0) {
      int scrollLines = Math.max(1, Math.min(caretLine, getLineCountWhole()));
      int scrollOffset;
      caretLine -= scrollLines;
      if (isBidi()) {
        int offsetDirection[] = getBidiOffsetAtMouseLocation(columnX, caretLine);
        caretOffset = offsetDirection[0];
        lastCaretDirection = offsetDirection[1];
      } else {
        caretOffset = getOffsetAtMouseLocation(columnX, caretLine);
      }
      scrollOffset = Math.max(0, verticalScrollOffset - (scrollLines * getVerticalIncrement()));
      if (scrollOffset < verticalScrollOffset) {
        setVerticalScrollOffset(scrollOffset, true);
      }
    }
    showCaret(caretLine);
    columnX = oldColumnX;
  }
}
