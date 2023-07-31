class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    int[] x = new int[1];
    int[] y = new int[1];
    OS.gtk_window_get_position(shellHandle, x, y);
    int width = OS.GTK_WIDGET_WIDTH(vboxHandle);
    int height = OS.GTK_WIDGET_HEIGHT(vboxHandle);
    return new Rectangle(x[0], y[0], width + trimWidth(), height + trimHeight());
  }
}
