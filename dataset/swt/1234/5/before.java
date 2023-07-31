class PlaceHold {
  void createHandle(int index, boolean fixed, boolean scrolled) {
    if (scrolled) {
      if (fixed) {
        fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
        if (fixedHandle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.gtk_widget_set_has_window(fixedHandle, true);
      }
      long vadj = OS.gtk_adjustment_new(0, 0, 100, 1, 10, 10);
      if (vadj == 0) {
        error(ERROR_NO_HANDLES);
      }
      long hadj = OS.gtk_adjustment_new(0, 0, 100, 1, 10, 10);
      if (hadj == 0) {
        error(ERROR_NO_HANDLES);
      }
      scrolledHandle = OS.gtk_scrolled_window_new(hadj, vadj);
      if (scrolledHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
    }
    handle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_widget_set_has_window(handle, true);
    gtk_widget_set_can_focus(handle, true);
    if ((style & SWT.EMBEDDED) == 0) {
      if ((state & CANVAS) != 0) {
        if (display.getData(NO_INPUT_METHOD) == null) {
          imHandle = OS.gtk_im_multicontext_new();
          if (imHandle == 0) {
            error(ERROR_NO_HANDLES);
          }
        }
      }
    }
    if (scrolled) {
      if (fixed) {
        OS.gtk_container_add(fixedHandle, scrolledHandle);
      }
      boolean warnings = display.getWarnings();
      display.setWarnings(false);
      OS.gtk_container_add(scrolledHandle, handle);
      display.setWarnings(warnings);
      int hsp = ((style & SWT.H_SCROLL) != 0) ? OS.GTK_POLICY_ALWAYS : OS.GTK_POLICY_NEVER;
      int vsp = ((style & SWT.V_SCROLL) != 0) ? OS.GTK_POLICY_ALWAYS : OS.GTK_POLICY_NEVER;
      OS.gtk_scrolled_window_set_policy(scrolledHandle, hsp, vsp);
      if (hasBorder()) {
        OS.gtk_scrolled_window_set_shadow_type(scrolledHandle, GTK_SHADOW_ETCHED_IN);
      }
    }
    if ((style & SWT.EMBEDDED) != 0) {
      socketHandle = OS.gtk_socket_new();
      if (socketHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(handle, socketHandle);
    }
    if (((style & SWT.NO_REDRAW_RESIZE) != 0) && ((style & SWT.RIGHT_TO_LEFT) == 0)) {
      OS.gtk_widget_set_redraw_on_allocate(handle, false);
    }
    if (((style & SWT.DOUBLE_BUFFERED) == 0) && ((style & SWT.NO_BACKGROUND) != 0)) {
      OS.gtk_widget_set_double_buffered(handle, false);
    }
  }
}
