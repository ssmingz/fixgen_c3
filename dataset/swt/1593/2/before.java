class PlaceHold {
  public void redraw() {
    super.redraw();
    text.redraw();
    arrow.redraw();
    if (popup.isVisible()) {
      list.redraw();
    }
  }
}
