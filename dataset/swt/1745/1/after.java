class PlaceHold {
  void handleMouseDoubleClick(Event event) {
    if ((event.button != 1) || (doubleClickEnabled == false)) {
      return;
    }
    event.y -= topMargin;
    mouseDoubleClick = true;
    caretOffset = getWordStart(caretOffset);
    resetSelection();
    caretOffset = getWordEndNoSpaces(caretOffset);
    getAccessible().textCaretMoved(caretOffset);
    showCaret();
    doMouseSelection();
    doubleClickSelection = new Point(selection.x, selection.y);
  }
}
