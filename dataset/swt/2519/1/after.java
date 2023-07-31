class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | THEME_BACKGROUND;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    gtk_widget_set_has_window(fixedHandle, true);
    handle = OS.gtk_frame_new(null);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    labelHandle = OS.gtk_label_new(null);
    if (labelHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.g_object_ref(labelHandle);
    g_object_ref_sink(labelHandle);
    clientHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (clientHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if (OS.GTK3) {
      OS.gtk_widget_override_background_color(clientHandle, GTK_STATE_FLAG_NORMAL, new GdkRGBA());
      long region = OS.gdk_region_new();
      OS.gtk_widget_input_shape_combine_region(clientHandle, region);
      OS.gdk_region_destroy(region);
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
