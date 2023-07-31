class PlaceHold {
  long hoverProc(long widget) {
    int[] x = new int[1];
    int[] y = new int[1];
    int[] mask = new int[1];
    gdk_window_get_device_position(0, x, y, mask);
    sendMouseEvent(MouseHover, 0, 0, x[0], y[0], false, mask[0]);
    return 0;
  }
}
