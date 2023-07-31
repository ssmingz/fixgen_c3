class PlaceHold {
  void doSelectionCursorNext() {
    int line = content.getLineAtOffset(caretOffset);
    int lineOffset = content.getOffsetAtLine(line);
    int offsetInLine = caretOffset - lineOffset;
    if (offsetInLine < content.getLine(line).length()) {
      lastCaretDirection = ST.COLUMN_NEXT;
      caretOffset++;
      showCaret();
    } else if ((line < (content.getLineCount() - 1)) && (isSingleLine() == false)) {
      line++;
      caretOffset = content.getOffsetAtLine(line);
      showCaret();
    }
  }
}
