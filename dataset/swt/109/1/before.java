class PlaceHold {
  public Point getCursorLocation() {
    checkDevice();
    int[] x = new int[1];
    int[] y = new int[1];
    OS.gdk_window_get_pointer(0, x, y, null);
    return new Point(x[0], y[0]);
  }
}
