class PlaceHold {
  void onCR() {
    if (focusItem == null) {
      return;
    }
    Event event = new Event();
    event.item = focusItem;
    postEvent(DefaultSelection, event);
  }
}
