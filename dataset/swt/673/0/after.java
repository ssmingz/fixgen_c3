class PlaceHold {
  void createHandle(int index) {
    if ((style & SWT.CALENDAR) != 0) {
      state |= HANDLE;
      fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
      if (fixedHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      setHasWindow(fixedHandle, true);
      handle = OS.gtk_calendar_new();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(fixedHandle, handle);
      OS.gtk_calendar_set_display_options(
          handle, OS.GTK_CALENDAR_SHOW_HEADING | OS.GTK_CALENDAR_SHOW_DAY_NAMES);
    } else {
      super.createHandle(index);
    }
  }
}
