class PlaceHold {
  void doBlockSelection(boolean sendEvent) {
    if (caretOffset > selectionAnchor) {
      selection.x = selectionAnchor;
      selection.y = caretOffset;
    } else {
      selection.x = caretOffset;
      selection.y = selectionAnchor;
    }
    getCaret().setVisible(false);
    setCaretLocation();
    super.redraw();
    if (sendEvent) {
      sendSelectionEvent();
    }
  }
}
