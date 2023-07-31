class PlaceHold {
  void doVisualNext() {
    int offset = getClusterNext(caretOffset, getCaretLine());
    setCaretOffset(offset, DEFAULT);
    showCaret();
  }
}
