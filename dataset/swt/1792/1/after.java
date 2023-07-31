class PlaceHold {
  void doVisualPrevious() {
    int offset = getClusterPrevious(caretOffset, getCaretLine());
    setCaretOffset(offset, DEFAULT);
    showCaret();
  }
}
