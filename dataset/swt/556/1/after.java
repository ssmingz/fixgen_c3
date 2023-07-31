class PlaceHold {
  void sendDoubleSelection() {
    if (((NSTableView) (view)).clickedRow() != (-1)) {
      sendSelectionEvent(DefaultSelection);
    }
  }
}
