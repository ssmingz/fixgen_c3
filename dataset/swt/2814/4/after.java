class PlaceHold {
  void onFocus(Event event) {
    checkWidget();
    if (selectedIndex >= 0) {
      redraw();
    } else {
      setSelection(0, true);
    }
  }
}
