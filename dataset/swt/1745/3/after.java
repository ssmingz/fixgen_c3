class PlaceHold {
  void doWordPrevious() {
    if ((selection.y - selection.x) > 0) {
      int caretLine;
      caretOffset = selection.x;
      getAccessible().textCaretMoved(caretOffset);
      caretLine = getCaretLine();
      showCaret(caretLine);
    } else {
      doSelectionWordPrevious();
    }
  }
}
