class PlaceHold {
  void doSelectionWordPrevious() {
    caretOffset = getWordStart(caretOffset);
    showCaret();
  }
}
