class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    gtk_widget_set_has_window(fixedHandle, true);
    int hAdjustment = OS.gtk_adjustment_new(0, 0, 100, 1, 10, 10);
    if (hAdjustment == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((style & SWT.HORIZONTAL) != 0) {
      handle = OS.gtk_hscrollbar_new(hAdjustment);
    } else {
      handle = OS.gtk_vscrollbar_new(hAdjustment);
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((OS.GTK_VERSION < OS.VERSION(2, 10, 0)) || ((style & SWT.VERTICAL) != 0)) {
      gtk_widget_set_can_focus(handle, true);
    }
    OS.gtk_container_add(fixedHandle, handle);
  }
}
