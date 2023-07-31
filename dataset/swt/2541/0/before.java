class PlaceHold {
  void doFocusOut() {
    if (focusItem != null) {
      redrawItem(focusItem.availableIndex);
    }
  }
}
