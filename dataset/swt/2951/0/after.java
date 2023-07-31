class PlaceHold {
  int paintHandle() {
    int topHandle = topHandle();
    int paintHandle = handle;
    while (paintHandle != topHandle) {
      if (gtk_widget_get_has_window(paintHandle)) {
        break;
      }
      paintHandle = OS.gtk_widget_get_parent(paintHandle);
    }
    return paintHandle;
  }
}
