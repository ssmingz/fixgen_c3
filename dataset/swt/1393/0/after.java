class PlaceHold {
  void doSelectionWordNext() {
    lastCaretDirection = ST.COLUMN_NEXT;
    caretOffset = getWordEnd(caretOffset);
    showCaret();
  }
}
