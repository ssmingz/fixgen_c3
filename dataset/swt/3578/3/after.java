class PlaceHold {
  void doSelection(int direction) {
    int redrawStart = -1;
    int redrawEnd = -1;
    if (selectionAnchor == (-1)) {
      selectionAnchor = selection.x;
    }
    if (direction == ST.COLUMN_PREVIOUS) {
      if (caretOffset < selection.x) {
        redrawEnd = selection.x;
        redrawStart = selection.x = caretOffset;
        if (selection.y != selectionAnchor) {
          redrawEnd = selection.y;
          selection.y = selectionAnchor;
        }
      } else if ((selectionAnchor == selection.x) && (caretOffset < selection.y)) {
        redrawEnd = selection.y;
        redrawStart = selection.y = caretOffset;
      }
    } else if (caretOffset > selection.y) {
      redrawStart = selection.y;
      redrawEnd = selection.y = caretOffset;
      if (selection.x != selectionAnchor) {
        redrawStart = selection.x;
        selection.x = selectionAnchor;
      }
    } else if ((selectionAnchor == selection.y) && (caretOffset > selection.x)) {
      redrawStart = selection.x;
      redrawEnd = selection.x = caretOffset;
    }
    if ((redrawStart != (-1)) && (redrawEnd != (-1))) {
      internalRedrawRange(redrawStart, redrawEnd - redrawStart);
      sendSelectionEvent();
    }
    sendAccessibleTextCaretMoved();
  }
}
