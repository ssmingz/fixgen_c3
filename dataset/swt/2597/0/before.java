class PlaceHold {
  void setInitialBounds() {
    Monitor monitor = getMonitor();
    Rectangle rect = monitor.getClientArea();
    int width = (rect.width * 5) / 8;
    int height = (rect.height * 5) / 8;
    OS.gtk_window_resize(shellHandle, width, height);
    resizeBounds(width, height, false);
  }
}
