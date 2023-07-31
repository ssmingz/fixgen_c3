class PlaceHold {
  void doWordNext() {
    if ((selection.y - selection.x) > 0) {
      caretOffset = selection.y;
      showCaret();
    } else {
      doSelectionWordNext();
    }
  }
}
