class PlaceHold {
  void sendMouseEvent(int type, XMotionEvent xEvent) {
    short[] x_root = new short[1];
    short[] y_root = new short[1];
    OS.XtTranslateCoords(handle, ((short) (0)), ((short) (0)), x_root, y_root);
    int x = xEvent.x_root - x_root[0];
    int y = xEvent.y_root - y_root[0];
    sendMouseEvent(type, 0, xEvent.time, x, y, xEvent.state);
  }
}
