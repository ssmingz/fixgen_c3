class PlaceHold {
  void doSelectionWordNext() {
    caretOffset = getWordEnd(caretOffset);
    showCaret();
  }
}
