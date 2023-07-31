class PlaceHold {
  long paintHandle() {
    long topHandle = topHandle();
    long paintHandle = handle;
    while (paintHandle != topHandle) {
      if (gtk_widget_get_has_window(paintHandle)) {
        break;
      }
      paintHandle = OS.gtk_widget_get_parent(paintHandle);
    }
    return paintHandle;
  }
}
