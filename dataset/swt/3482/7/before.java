class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | THEME_BACKGROUND;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
      OS.gtk_widget_set_has_window(fixedHandle, true);
    } else {
      OS.gtk_fixed_set_has_window(fixedHandle, true);
    }
    handle = OS.gtk_toolbar_new();
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_container_add(fixedHandle, handle);
    if ((style & SWT.FLAT) != 0) {
      byte[] swt_toolbar_flat = Converter.wcsToMbcs(null, "swt-toolbar-flat", true);
      OS.gtk_widget_set_name(handle, swt_toolbar_flat);
    }
  }
}
