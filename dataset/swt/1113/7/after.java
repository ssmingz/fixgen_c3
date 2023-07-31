class PlaceHold {
  @Override
  void createHandle(int index) {
    state |= HANDLE;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_widget_set_has_window(fixedHandle, true);
    long hAdjustment = OS.gtk_adjustment_new(0, 0, 100, 1, 10, 10);
    if (hAdjustment == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((style & SWT.HORIZONTAL) != 0) {
      handle = gtk_scrollbar_new(GTK_ORIENTATION_HORIZONTAL, hAdjustment);
    } else {
      handle = gtk_scrollbar_new(GTK_ORIENTATION_VERTICAL, hAdjustment);
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((style & SWT.VERTICAL) != 0) {
      OS.gtk_widget_set_can_focus(handle, true);
    }
    OS.gtk_container_add(fixedHandle, handle);
  }
}
