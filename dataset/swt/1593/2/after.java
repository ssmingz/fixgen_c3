class PlaceHold {
  public void redraw() {
    super.redraw();
    text.redraw();
    arrow.redraw();
    if (getPopup().isVisible()) {
      list.redraw();
    }
  }
}
