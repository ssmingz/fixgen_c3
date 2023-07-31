class PlaceHold {
  void resizeHandle(int width, int height) {
    int topHandle = topHandle();
    int flags = OS.GTK_WIDGET_FLAGS(topHandle);
    OS.GTK_WIDGET_SET_FLAGS(topHandle, GTK_VISIBLE);
    OS.gtk_widget_set_size_request(fixedHandle, width, height);
    OS.gtk_widget_set_size_request(frameHandle, width, height);
    OS.gtk_widget_set_size_request(handle, width, height);
    int parentHandle = parent.parentingHandle();
    Display display = getDisplay();
    boolean warnings = display.getWarnings();
    display.setWarnings(false);
    OS.gtk_container_resize_children(parentHandle);
    display.setWarnings(warnings);
    if ((flags & OS.GTK_VISIBLE) == 0) {
      OS.GTK_WIDGET_UNSET_FLAGS(topHandle, GTK_VISIBLE);
    }
  }
}
