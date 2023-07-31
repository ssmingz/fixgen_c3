class PlaceHold {
  void onFocus() {
    if (focusIndex != (-1)) {
      items[focusIndex].redraw();
    }
  }
}
