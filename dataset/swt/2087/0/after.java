class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | MENU;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    gtk_widget_set_has_window(fixedHandle, true);
    long adjustment = OS.gtk_adjustment_new(0, 0, 100, 1, 10, 0);
    if (adjustment == 0) {
      error(ERROR_NO_HANDLES);
    }
    handle = OS.gtk_spin_button_new(adjustment, climbRate, 0);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_container_add(fixedHandle, handle);
    OS.gtk_editable_set_editable(handle, (style & SWT.READ_ONLY) == 0);
    OS.gtk_entry_set_has_frame(handle, (style & SWT.BORDER) != 0);
    OS.gtk_spin_button_set_wrap(handle, (style & SWT.WRAP) != 0);
    if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
      imContext = OS.imContextLast();
    }
  }
}
