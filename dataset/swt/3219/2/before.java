class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    handle = OS.gtk_expander_new(null);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    clientHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (clientHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_container_add(handle, clientHandle);
    boxHandle = OS.gtk_hbox_new(false, 4);
    if (boxHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    labelHandle = OS.gtk_label_new(null);
    if (labelHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    imageHandle = OS.gtk_image_new();
    if (imageHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_container_add(boxHandle, imageHandle);
    OS.gtk_container_add(boxHandle, labelHandle);
    OS.gtk_expander_set_label_widget(handle, boxHandle);
    OS.GTK_WIDGET_SET_FLAGS(handle, GTK_CAN_FOCUS);
  }
}
