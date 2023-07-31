class PlaceHold {
  void setSelection(int start, int length, boolean sendEvent, boolean doBlock) {
    int end = start + length;
    if (start > end) {
      int temp = end;
      end = start;
      start = temp;
    }
    if ((((selection.x != start) || (selection.y != end))
            || ((length > 0) && (selectionAnchor != selection.x)))
        || ((length < 0) && (selectionAnchor != selection.y))) {
      if (blockSelection && doBlock) {
        setBlockSelectionOffset(start, end, sendEvent);
      } else {
        clearSelection(sendEvent);
        if (length < 0) {
          selectionAnchor = selection.y = end;
          selection.x = start;
          setCaretOffset(start, PREVIOUS_OFFSET_TRAILING);
        } else {
          selectionAnchor = selection.x = start;
          selection.y = end;
          setCaretOffset(end, PREVIOUS_OFFSET_TRAILING);
        }
        internalRedrawRange(selection.x, selection.y - selection.x);
      }
    }
  }
}
