class PlaceHold {
  void doBidiMouseLocationChange(int x, int y, boolean select) {
    int line = (y + verticalScrollOffset) / lineHeight;
    int lineCount = content.getLineCount();
    if (line > (lineCount - 1)) {
      line = lineCount - 1;
    }
    if ((line == 0) || ((isSingleLine() == false) && (line > 0))) {
      int newCaretOffset = getBidiOffsetAtMouseLocation(x, line);
      if ((x >= 0)
          || (content.getLineAtOffset(newCaretOffset) != content.getLineAtOffset(caretOffset))) {
        caretOffset = newCaretOffset;
        caretLine = line;
        if (select) {
          doMouseSelection();
        }
        setBidiCaretLocation(null);
        setBidiKeyboardLanguage();
      }
      if (select == false) {
        clearSelection(true);
      }
    }
  }
}
