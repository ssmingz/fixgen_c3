class PlaceHold {
  void redrawItem() {
    if (!isAvailable()) {
      return;
    }
    parent.redraw(0, parent.getItemY(this), parent.getClientArea().width, parent.itemHeight, false);
  }
}
