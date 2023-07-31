class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    fixedHandle = OS.gtk_fixed_new();
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_fixed_set_has_window(fixedHandle, true);
    scrolledHandle = OS.gtk_scrolled_window_new(0, 0);
    if (scrolledHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    handle = OS.gtk_clist_new(1);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int parentHandle = parent.parentingHandle();
    OS.gtk_container_add(parentHandle, fixedHandle);
    OS.gtk_container_add(fixedHandle, scrolledHandle);
    OS.gtk_container_add(scrolledHandle, handle);
    OS.gtk_widget_show(fixedHandle);
    OS.gtk_widget_show(scrolledHandle);
    OS.gtk_widget_show(handle);
    OS.gtk_clist_set_row_height(handle, 0);
    int mode = ((style & SWT.MULTI) != 0) ? OS.GTK_SELECTION_EXTENDED : OS.GTK_SELECTION_BROWSE;
    OS.gtk_clist_set_selection_mode(handle, mode);
    if ((style & SWT.BORDER) != 0) {
      OS.gtk_clist_set_shadow_type(handle, GTK_SHADOW_ETCHED_IN);
    }
    int hsp = ((style & SWT.H_SCROLL) != 0) ? OS.GTK_POLICY_AUTOMATIC : OS.GTK_POLICY_NEVER;
    int vsp = ((style & SWT.V_SCROLL) != 0) ? OS.GTK_POLICY_AUTOMATIC : OS.GTK_POLICY_NEVER;
    OS.gtk_scrolled_window_set_policy(scrolledHandle, hsp, vsp);
  }
}
