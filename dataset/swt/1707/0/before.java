class PlaceHold {
  void onFocusOut() {
    if (focusItem != null) {
      redrawItem(focusItem.index, true);
    }
  }
}
