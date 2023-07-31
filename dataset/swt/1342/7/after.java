class PlaceHold {
  void resizeHandle(int width, int height) {
    long topHandle = topHandle();
    OS.gtk_widget_set_size_request(topHandle, width, height);
    if (topHandle != handle) {
      OS.gtk_widget_set_size_request(handle, width, height);
    }
  }
}
