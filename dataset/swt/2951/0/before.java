class PlaceHold {
  int paintHandle() {
    int topHandle = topHandle();
    int paintHandle = handle;
    while (paintHandle != topHandle) {
      if ((OS.GTK_WIDGET_FLAGS(paintHandle) & OS.GTK_NO_WINDOW) == 0) {
        break;
      }
      paintHandle = OS.gtk_widget_get_parent(paintHandle);
    }
    return paintHandle;
  }
}
