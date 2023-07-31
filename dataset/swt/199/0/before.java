class PlaceHold {
  void clearSelection(boolean sendEvent) {
    int selectionStart = selection.x;
    int selectionEnd = selection.y;
    int length = content.getCharCount();
    resetSelection();
    if ((selectionEnd - selectionStart) > 0) {
      int redrawStart = Math.min(selectionStart, length);
      int redrawEnd = Math.min(selectionEnd, length);
      if ((redrawEnd - redrawStart) > 0) {
        redrawRange(redrawStart, redrawEnd - redrawStart, true);
      }
      if (sendEvent == true) {
        sendSelectionEvent();
      }
    }
  }
}
