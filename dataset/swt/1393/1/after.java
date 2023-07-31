class PlaceHold {
  void doSelectionWordPrevious() {
    lastCaretDirection = ST.COLUMN_PREVIOUS;
    caretOffset = getWordStart(caretOffset);
    showCaret();
  }
}
