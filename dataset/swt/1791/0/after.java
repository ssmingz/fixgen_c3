class PlaceHold {
  public void redraw(int x, int y, int width, int height, boolean all) {
    checkWidget();
    if (drawCount <= 0) {
      super.redraw(x, y, width, height, all);
    }
  }
}
