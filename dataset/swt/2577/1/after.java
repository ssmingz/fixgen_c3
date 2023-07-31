class PlaceHold {
  void tableViewSelectionDidChange(int id, int sel, int aNotification) {
    if (didSelect) {
      return;
    }
    sendSelection();
  }
}
