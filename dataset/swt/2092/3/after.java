class PlaceHold {
  void outlineViewSelectionDidChange(int notification) {
    if (ignoreSelect) {
      return;
    }
    postEvent(Selection);
  }
}
