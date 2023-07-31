class PlaceHold {
  void redrawItem() {
    parent.redraw(0, parent.getItemY(this), parent.getClientArea().width, parent.itemHeight, false);
  }
}
