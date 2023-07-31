class PlaceHold {
  void handleKeyUp(Event event) {
    if (clipboardSelection != null) {
      if ((clipboardSelection.x != selection.x) || (clipboardSelection.y != selection.y)) {
        copySelection(SELECTION_CLIPBOARD);
      }
    }
    clipboardSelection = null;
  }
}
