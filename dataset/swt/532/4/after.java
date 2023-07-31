class PlaceHold {
  public Point toControl(int x, int y) {
    checkWidget();
    long window = eventWindow();
    int[] origin_x = new int[1];
    int[] origin_y = new int[1];
    OS.gdk_window_get_origin(window, origin_x, origin_y);
    x -= origin_x[0];
    y -= origin_y[0];
    if ((style & SWT.MIRRORED) != 0) {
      x = getClientWidth() - x;
    }
    return new Point(x, y);
  }
}
