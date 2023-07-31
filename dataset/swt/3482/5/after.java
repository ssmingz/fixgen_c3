class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | THEME_BACKGROUND;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    setHasWindow(fixedHandle, true);
    handle = OS.gtk_frame_new(null);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    labelHandle = OS.gtk_label_new(null);
    if (labelHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.g_object_ref(labelHandle);
    OS.gtk_object_sink(labelHandle);
    clientHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (clientHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_container_add(fixedHandle, handle);
    OS.gtk_container_add(handle, clientHandle);
    if ((style & SWT.SHADOW_IN) != 0) {
      OS.gtk_frame_set_shadow_type(handle, GTK_SHADOW_IN);
    }
    if ((style & SWT.SHADOW_OUT) != 0) {
      OS.gtk_frame_set_shadow_type(handle, GTK_SHADOW_OUT);
    }
    if ((style & SWT.SHADOW_ETCHED_IN) != 0) {
      OS.gtk_frame_set_shadow_type(handle, GTK_SHADOW_ETCHED_IN);
    }
    if ((style & SWT.SHADOW_ETCHED_OUT) != 0) {
      OS.gtk_frame_set_shadow_type(handle, GTK_SHADOW_ETCHED_OUT);
    }
  }
}
