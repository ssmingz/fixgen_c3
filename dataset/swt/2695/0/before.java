class PlaceHold {
  public Point getSize() {
    checkWidget();
    int width = OS.GTK_WIDGET_WIDTH(vboxHandle);
    int height = OS.GTK_WIDGET_HEIGHT(vboxHandle);
    return new Point(width + trimWidth(), height + trimHeight());
  }
}
