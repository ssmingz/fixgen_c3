class PlaceHold {
  void handleKeyUp(Event event) {
    if (clipboardSelection != null) {
      if ((clipboardSelection.x != selection.x) || (clipboardSelection.y != selection.y)) {
        try {
          if ((selection.y - selection.x) > 0) {
            setClipboardContent(selection.x, selection.y - selection.x, SELECTION_CLIPBOARD);
          }
        } catch (SWTError error) {
          if (error.code != DND.ERROR_CANNOT_SET_CLIPBOARD) {
            throw error;
          }
        }
      }
    }
    clipboardSelection = null;
  }
}
