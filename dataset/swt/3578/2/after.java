class PlaceHold {
  void doBlockSelection(boolean sendEvent) {
    if (caretOffset > selectionAnchor) {
      selection.x = selectionAnchor;
      selection.y = caretOffset;
    } else {
      selection.x = caretOffset;
      selection.y = selectionAnchor;
    }
    updateCaretVisibility();
    setCaretLocation();
    super.redraw();
    if (sendEvent) {
      sendSelectionEvent();
    }
    sendAccessibleTextCaretMoved();
  }
}
