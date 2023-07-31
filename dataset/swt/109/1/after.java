class PlaceHold {
  public Point getCursorLocation() {
    checkDevice();
    int[] x = new int[1];
    int[] y = new int[1];
    gdk_window_get_device_position(0, x, y, null);
    return new Point(x[0], y[0]);
  }
}
