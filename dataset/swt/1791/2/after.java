class PlaceHold {
  public void redraw() {
    checkWidget();
    if (drawCount <= 0) {
      super.redraw();
    }
  }
}
