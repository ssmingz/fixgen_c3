class PlaceHold {
  void doVisualNext() {
    caretOffset = getClusterNext(caretOffset, getCaretLine());
    showCaret();
  }
}
