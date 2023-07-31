class PlaceHold {
  void handleMouseUp(Event event) {
    clickCount = 0;
    endAutoScroll();
    if (event.button == 1) {
      copySelection(SELECTION_CLIPBOARD);
    }
  }
}
