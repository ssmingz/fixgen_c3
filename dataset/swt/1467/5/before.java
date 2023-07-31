class PlaceHold {
  void doCursorPrevious() {
    if ((selection.y - selection.x) > 0) {
      int caretLine;
      caretOffset = selection.x;
      caretLine = getCaretLine();
      showCaret(caretLine);
    } else {
      doSelectionCursorPrevious();
    }
  }
}
