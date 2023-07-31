class PlaceHold {
  void doPageEnd() {
    if (isSingleLine()) {
      doLineEnd();
    } else {
      int line = getBottomIndex();
      int bottomCaretOffset = content.getOffsetAtLine(line) + content.getLine(line).length();
      if (caretOffset < bottomCaretOffset) {
        caretOffset = bottomCaretOffset;
        caretLine = line;
        showCaret();
      }
    }
  }
}
