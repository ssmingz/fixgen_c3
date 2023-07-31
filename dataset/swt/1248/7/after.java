class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_fixed_set_has_window(fixedHandle, true);
    handle = OS.gtk_notebook_new();
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_container_add(fixedHandle, handle);
    OS.gtk_notebook_set_scrollable(handle, true);
    OS.gtk_notebook_set_show_tabs(handle, true);
    if ((style & SWT.BOTTOM) != 0) {
      OS.gtk_notebook_set_tab_pos(handle, GTK_POS_BOTTOM);
    }
  }
}
