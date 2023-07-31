class PlaceHold {
  void doVisualPrevious() {
    caretOffset = getClusterPrevious(caretOffset, getCaretLine());
    showCaret();
  }
}
