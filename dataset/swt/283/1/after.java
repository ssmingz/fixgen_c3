class PlaceHold {
  void setFirstItem(int index) {
    if ((index < 0) || (index > (items.length - 1))) {
      return;
    }
    if (index == firstIndex) {
      return;
    }
    firstIndex = index;
    setItemLocation();
    setButtonBounds();
    redrawTabs();
  }
}
