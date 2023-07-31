class PlaceHold {
  public void redraw() {
    checkWidget();
    if (!OS.GTK_WIDGET_VISIBLE(topHandle())) {
      return;
    }
    int paintHandle = paintHandle();
    int width = OS.GTK_WIDGET_WIDTH(paintHandle);
    int height = OS.GTK_WIDGET_HEIGHT(paintHandle);
    redrawWidget(0, 0, width, height, false);
  }
}
