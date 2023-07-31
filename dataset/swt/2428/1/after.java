class PlaceHold {
  void doWordNext() {
    if ((selection.y - selection.x) > 0) {
      setCaretOffset(selection.y, DEFAULT);
      showCaret();
    } else {
      doSelectionWordNext();
    }
  }
}
