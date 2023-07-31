class PlaceHold {
  void sendSelectionEvent() {
    getAccessible().textSelectionChanged();
    Event event = new Event();
    event.x = selection.x;
    event.y = selection.y;
    notifyListeners(Selection, event);
    try {
      if ((selection.y - selection.x) > 0) {
        setClipboardContent(selection.x, selection.y - selection.x, SELECTION_CLIPBOARD);
      } else {
        clipboard.clearContents(SELECTION_CLIPBOARD);
      }
    } catch (SWTError error) {
      if (error.code != DND.ERROR_CANNOT_SET_CLIPBOARD) {
        throw error;
      }
    }
  }
}
