class PlaceHold {
  void tableViewSelectionDidChange(int aNotification) {
    if (ignoreSelect) {
      return;
    }
    postEvent(Selection);
  }
}
