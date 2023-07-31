class PlaceHold {
  public Point toDisplay(int x, int y) {
    checkWidget();
    long window = eventWindow();
    int[] origin_x = new int[1];
    int[] origin_y = new int[1];
    OS.gdk_window_get_origin(window, origin_x, origin_y);
    if ((style & SWT.MIRRORED) != 0) {
      x = getClientWidth() - x;
    }
    x += origin_x[0];
    y += origin_y[0];
    return new Point(x, y);
  }
}
