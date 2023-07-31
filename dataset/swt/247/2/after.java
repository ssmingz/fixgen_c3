class PlaceHold {
  public void redraw(int x, int y, int width, int height, boolean all) {
    checkWidget();
    if (!OS.GTK_WIDGET_VISIBLE(topHandle())) {
      return;
    }
    redrawWidget(x, y, width, height, all);
  }
}
