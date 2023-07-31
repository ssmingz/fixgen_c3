class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    gtk_widget_set_has_window(fixedHandle, true);
    handle = OS.gtk_vbox_new(false, 0);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((style & SWT.V_SCROLL) != 0) {
      scrolledHandle = OS.gtk_scrolled_window_new(0, 0);
      if (scrolledHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_scrolled_window_set_policy(scrolledHandle, GTK_POLICY_NEVER, GTK_POLICY_AUTOMATIC);
      OS.gtk_container_add(fixedHandle, scrolledHandle);
      OS.gtk_scrolled_window_add_with_viewport(scrolledHandle, handle);
      int viewport = OS.gtk_bin_get_child(scrolledHandle);
      OS.gtk_viewport_set_shadow_type(viewport, GTK_SHADOW_NONE);
    } else {
      OS.gtk_container_add(fixedHandle, handle);
    }
    OS.gtk_container_set_border_width(handle, 0);
  }
}
