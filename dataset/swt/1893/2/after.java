class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int parentHandle = parent.parentingHandle();
    if ((style & SWT.SINGLE) != 0) {
      handle = OS.gtk_entry_new();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(parentHandle, handle);
      OS.gtk_entry_set_editable(handle, (style & SWT.READ_ONLY) == 0);
    } else {
      fixedHandle = OS.gtk_fixed_new();
      if (fixedHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_fixed_set_has_window(fixedHandle, true);
      scrolledHandle = OS.gtk_scrolled_window_new(0, 0);
      if (scrolledHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      handle = OS.gtk_text_new(0, 0);
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(parentHandle, fixedHandle);
      OS.gtk_container_add(fixedHandle, scrolledHandle);
      Display display = getDisplay();
      boolean warnings = display.getWarnings();
      display.setWarnings(false);
      OS.gtk_container_add(scrolledHandle, handle);
      display.setWarnings(warnings);
      OS.gtk_widget_show(fixedHandle);
      OS.gtk_widget_show(scrolledHandle);
      OS.gtk_text_set_editable(handle, (style & SWT.READ_ONLY) == 0);
      OS.gtk_text_set_word_wrap(handle, (style & SWT.WRAP) != 0 ? 1 : 0);
      int hsp = ((style & SWT.H_SCROLL) != 0) ? OS.GTK_POLICY_AUTOMATIC : OS.GTK_POLICY_NEVER;
      int vsp = ((style & SWT.V_SCROLL) != 0) ? OS.GTK_POLICY_AUTOMATIC : OS.GTK_POLICY_NEVER;
      OS.gtk_scrolled_window_set_policy(scrolledHandle, hsp, vsp);
    }
    OS.gtk_widget_show(handle);
  }
}
