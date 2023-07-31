class PlaceHold {
  void clearBlockSelection(boolean reset, boolean sendEvent) {
    if (reset) {
      resetSelection();
    }
    blockXAnchor = blockYAnchor = -1;
    blockXLocation = blockYLocation = -1;
    caretDirection = SWT.NULL;
    getCaret().setVisible(true);
    super.redraw();
    if (sendEvent) {
      sendSelectionEvent();
    }
  }
}
