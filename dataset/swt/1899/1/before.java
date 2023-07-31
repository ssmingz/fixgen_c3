class PlaceHold {
  public Rectangle getClientArea() {
    checkWidget();
    if ((state & ZERO_SIZED) != 0) {
      return new Rectangle(0, 0, 0, 0);
    }
    int width = OS.GTK_WIDGET_WIDTH(clientHandle);
    int height = OS.GTK_WIDGET_HEIGHT(clientHandle);
    return new Rectangle(0, 0, width, height);
  }
}
