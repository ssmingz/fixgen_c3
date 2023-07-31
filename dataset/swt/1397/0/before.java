class PlaceHold {
  void handleMouseUp(Event event) {
    clickCount = 0;
    endAutoScroll();
    if (event.button == 1) {
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
}
