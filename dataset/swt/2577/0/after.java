class PlaceHold {
  void outlineViewSelectionDidChange(int id, int sel, int notification) {
    if (didSelect) {
      return;
    }
    sendSelection();
  }
}
