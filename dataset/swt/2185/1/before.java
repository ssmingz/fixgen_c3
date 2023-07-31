class PlaceHold {
  void sendSelectionEvent() {
    getAccessible().textSelectionChanged();
    Event event = new Event();
    event.x = selection.x;
    event.y = selection.y;
    notifyListeners(Selection, event);
  }
}
