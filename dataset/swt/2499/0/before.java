class PlaceHold {
  void setInitialBounds() {
    Monitor monitor = getMonitor();
    Rectangle rect = monitor.getClientArea();
    int width = (rect.width * 5) / 8;
    int height = (rect.height * 5) / 8;
    OS.gtk_widget_set_size_request(scrolledHandle, width, height);
    OS.gtk_window_resize(shellHandle, width, height);
    OS.gtk_container_resize_children(fixedHandle);
  }
}
