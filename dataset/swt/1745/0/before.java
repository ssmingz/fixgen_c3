class PlaceHold {
  void doWordNext() {
    if ((selection.y - selection.x) > 0) {
      int caretLine;
      caretOffset = selection.y;
      caretLine = getCaretLine();
      showCaret(caretLine);
    } else {
      doSelectionWordNext();
    }
  }
}
