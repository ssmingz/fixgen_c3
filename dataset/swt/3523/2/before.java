class PlaceHold {
  void createScrolledHandle(int parentHandle) {
    boolean isScrolled = true;
    if (isScrolled) {
      fixedHandle = OS.gtk_fixed_new();
      if (fixedHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_fixed_set_has_window(fixedHandle, true);
      int vadj = OS.gtk_adjustment_new(0, 0, 100, 1, 10, 10);
      if (vadj == 0) {
        error(ERROR_NO_HANDLES);
      }
      int hadj = OS.gtk_adjustment_new(0, 0, 100, 1, 10, 10);
      if (hadj == 0) {
        error(ERROR_NO_HANDLES);
      }
      scrolledHandle = OS.gtk_scrolled_window_new(hadj, vadj);
      if (scrolledHandle == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
    }
    handle = OS.gtk_fixed_new();
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.gtk_fixed_set_has_window(handle, true);
    OS.GTK_WIDGET_SET_FLAGS(handle, GTK_CAN_FOCUS);
    if (isScrolled) {
      OS.gtk_container_add(parentHandle, fixedHandle);
      OS.gtk_container_add(fixedHandle, scrolledHandle);
      Display display = getDisplay();
      boolean warnings = display.getWarnings();
      display.setWarnings(false);
      OS.gtk_container_add(scrolledHandle, handle);
      display.setWarnings(warnings);
      OS.gtk_widget_show(fixedHandle);
      OS.gtk_widget_show(scrolledHandle);
      int hsp = ((style & SWT.H_SCROLL) == 0) ? OS.GTK_POLICY_NEVER : OS.GTK_POLICY_ALWAYS;
      int vsp = ((style & SWT.V_SCROLL) == 0) ? OS.GTK_POLICY_NEVER : OS.GTK_POLICY_ALWAYS;
      OS.gtk_scrolled_window_set_policy(scrolledHandle, hsp, vsp);
      if ((style & SWT.BORDER) != 0) {
        OS.gtk_scrolled_window_set_shadow_type(scrolledHandle, GTK_SHADOW_ETCHED_IN);
      }
    } else {
      OS.gtk_container_add(parentHandle, handle);
    }
    OS.gtk_widget_show(handle);
    OS.gtk_widget_set_double_buffered(handle, false);
    if ((style & SWT.NO_BACKGROUND) != 0) {
      setBackgroundPixmap();
    }
    if ((style & SWT.NO_REDRAW_RESIZE) != 0) {
      OS.gtk_widget_set_redraw_on_allocate(handle, false);
    }
  }
}
