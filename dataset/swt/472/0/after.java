class PlaceHold {
  void doMouseLocationChange(int x, int y, boolean select) {
    int line = (y + verticalScrollOffset) / lineHeight;
    int lineCount = content.getLineCount();
    if (line > (lineCount - 1)) {
      line = lineCount - 1;
    }
    if ((line == 0) || ((isSingleLine() == false) && (line > 0))) {
      int newCaretOffset = getOffsetAtMouseLocation(x, line);
      if (newCaretOffset != caretOffset) {
        caretOffset = newCaretOffset;
        caretLine = line;
        if (select) {
          doMouseSelection();
        }
        setCaretLocation();
      }
      if (select == false) {
        clearSelection(true);
      }
    }
  }
}
