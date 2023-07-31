class PlaceHold {
  void onFocus() {
    if (focusItem != null) {
      focusItem.redraw();
    }
  }
}
