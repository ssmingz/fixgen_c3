class PlaceHold {
  void internalSetSelection(int start, int length, boolean sendEvent) {
    int end = start + length;
    if (start > end) {
      int temp = end;
      end = start;
      start = temp;
    }
    if ((((selection.x != start) || (selection.y != end))
            || ((length > 0) && (selectionAnchor != selection.x)))
        || ((length < 0) && (selectionAnchor != selection.y))) {
      clearSelection(sendEvent);
      if (length < 0) {
        selectionAnchor = selection.y = end;
        caretOffset = selection.x = start;
      } else {
        selectionAnchor = selection.x = start;
        caretOffset = selection.y = end;
      }
      internalRedrawRange(selection.x, selection.y - selection.x, true);
    }
  }
}
