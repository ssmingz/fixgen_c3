class PlaceHold {
  void resetSelection() {
    selection.x = selection.y = caretOffset;
    selectionAnchor = -1;
    sendAccessibleTextCaretMoved();
  }
}
