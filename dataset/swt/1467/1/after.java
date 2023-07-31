class PlaceHold {
  void doMouseLocationChange(int x, int y, boolean select) {
    int line = (y + verticalScrollOffset) / lineHeight;
    int lineCount = content.getLineCount();
    int newCaretOffset;
    int newCaretLine;
    int newCaretDirection = lastCaretDirection;
    if (line > (lineCount - 1)) {
      line = lineCount - 1;
    }
    if ((line < 0) || (isSingleLine() && (line > 0))) {
      return;
    }
    if (isBidi()) {
      int offsetDirection[] = getBidiOffsetAtMouseLocation(x, line);
      newCaretOffset = offsetDirection[0];
      newCaretDirection = offsetDirection[1];
    } else {
      newCaretOffset = getOffsetAtMouseLocation(x, line);
    }
    if (mouseDoubleClick) {
      newCaretOffset = doMouseWordSelect(x, newCaretOffset, line);
    }
    newCaretLine = content.getLineAtOffset(newCaretOffset);
    if (((y >= 0) && (y < getClientArea().height))
        && (((x >= 0) && (x < getClientArea().width))
            || (newCaretLine != content.getLineAtOffset(caretOffset)))) {
      if (newCaretOffset != caretOffset) {
        lastCaretDirection = newCaretDirection;
        caretOffset = newCaretOffset;
        getAccessible().textCaretMoved(caretOffset);
        if (select) {
          doMouseSelection();
        }
        showCaret();
      }
    }
    if (select == false) {
      lastCaretDirection = newCaretDirection;
      clearSelection(true);
    }
  }
}
