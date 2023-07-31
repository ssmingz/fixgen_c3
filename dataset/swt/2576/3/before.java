class PlaceHold {
  void doSelectionWordNext() {
    int newCaretOffset = getWordEnd(caretOffset);
    if ((isSingleLine() == false)
        || (content.getLineAtOffset(caretOffset) == content.getLineAtOffset(newCaretOffset))) {
      lastCaretDirection = ST.COLUMN_NEXT;
      caretOffset = newCaretOffset;
      showCaret();
    }
  }
}
