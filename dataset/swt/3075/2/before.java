class PlaceHold {
  void setChevronVisible(boolean visible) {
    checkWidget();
    if (chevronVisible == visible) {
      return;
    }
    chevronVisible = visible;
    updateItems();
    redraw();
  }
}
