class PlaceHold {
  void doPageDown(boolean select) {
    int lineCount = content.getLineCount();
    int oldColumnX = columnX;
    int caretLine;
    if (isSingleLine()) {
      return;
    }
    caretLine = getCaretLine();
    if (caretLine < (lineCount - 1)) {
      int verticalMaximum = lineCount * getVerticalIncrement();
      int pageSize = getClientArea().height;
      int scrollLines = Math.min((lineCount - caretLine) - 1, getLineCountWhole());
      int scrollOffset;
      scrollLines = Math.max(1, scrollLines);
      caretLine += scrollLines;
      if (isBidi()) {
        int offsetDirection[] = getBidiOffsetAtMouseLocation(columnX, caretLine);
        caretOffset = offsetDirection[0];
        lastCaretDirection = offsetDirection[1];
      } else {
        caretOffset = getOffsetAtMouseLocation(columnX, caretLine);
      }
      getAccessible().textCaretMoved(caretOffset);
      if (select) {
        doSelection(COLUMN_NEXT);
      }
      scrollOffset = verticalScrollOffset + (scrollLines * getVerticalIncrement());
      if ((scrollOffset + pageSize) > verticalMaximum) {
        scrollOffset = verticalMaximum - pageSize;
      }
      if (scrollOffset > verticalScrollOffset) {
        setVerticalScrollOffset(scrollOffset, true);
      }
    }
    showCaret(caretLine);
    columnX = oldColumnX;
  }
}
