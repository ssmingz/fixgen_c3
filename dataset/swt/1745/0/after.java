class PlaceHold {
  void doWordNext() {
    if ((selection.y - selection.x) > 0) {
      int caretLine;
      caretOffset = selection.y;
      getAccessible().textCaretMoved(caretOffset);
      caretLine = getCaretLine();
      showCaret(caretLine);
    } else {
      doSelectionWordNext();
    }
  }
}
