class PlaceHold {
  void onFocus(Event event) {
    checkWidget();
    if (selectedIndex >= 0) {
      redrawTabArea();
    } else {
      setSelection(0, true);
    }
  }
}
