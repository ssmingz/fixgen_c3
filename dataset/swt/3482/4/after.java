class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | THEME_BACKGROUND;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    setHasWindow(fixedHandle, true);
    int hAdjustment = OS.gtk_adjustment_new(0, 0, 100, 1, 10, 0);
    if (hAdjustment == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((style & SWT.HORIZONTAL) != 0) {
      handle = OS.gtk_hscale_new(hAdjustment);
    } else {
      handle = OS.gtk_vscale_new(hAdjustment);
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_container_add(fixedHandle, handle);
    OS.gtk_scale_set_digits(handle, 0);
    OS.gtk_scale_set_draw_value(handle, false);
  }
}
