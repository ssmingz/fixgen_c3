class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    fixedHandle = OS.gtk_fixed_new();
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_fixed_set_has_window(fixedHandle, true);
    handle = OS.gtk_toolbar_new();
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int parentHandle = parent.parentingHandle();
    OS.gtk_container_add(parentHandle, fixedHandle);
    OS.gtk_container_add(fixedHandle, handle);
    OS.gtk_widget_show(fixedHandle);
    OS.gtk_widget_show(handle);
    setForegroundColor(parent.getForegroundColor());
    setFontDescription(parent.getFontDescription());
  }
}
