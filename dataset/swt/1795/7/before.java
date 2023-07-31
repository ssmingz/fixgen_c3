class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_fixed_set_has_window(fixedHandle, true);
    scrolledHandle = OS.gtk_scrolled_window_new(0, 0);
    if (scrolledHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int[] types = getColumnTypes(1);
    modelHandle = OS.gtk_tree_store_newv(types.length, types);
    if (modelHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    handle = OS.gtk_tree_view_new_with_model(modelHandle);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((style & SWT.CHECK) != 0) {
      checkRenderer = OS.gtk_cell_renderer_toggle_new();
      if (checkRenderer == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.g_object_ref(checkRenderer);
    }
    createColumn(null, 0);
    OS.gtk_container_add(fixedHandle, scrolledHandle);
    OS.gtk_container_add(scrolledHandle, handle);
    int mode = ((style & SWT.MULTI) != 0) ? OS.GTK_SELECTION_MULTIPLE : OS.GTK_SELECTION_BROWSE;
    int selectionHandle = OS.gtk_tree_view_get_selection(handle);
    OS.gtk_tree_selection_set_mode(selectionHandle, mode);
    OS.gtk_tree_view_set_headers_visible(handle, false);
    int hsp = ((style & SWT.H_SCROLL) != 0) ? OS.GTK_POLICY_AUTOMATIC : OS.GTK_POLICY_NEVER;
    int vsp = ((style & SWT.V_SCROLL) != 0) ? OS.GTK_POLICY_AUTOMATIC : OS.GTK_POLICY_NEVER;
    OS.gtk_scrolled_window_set_policy(scrolledHandle, hsp, vsp);
    if ((style & SWT.BORDER) != 0) {
      OS.gtk_scrolled_window_set_shadow_type(scrolledHandle, GTK_SHADOW_ETCHED_IN);
    }
    if ((style & SWT.VIRTUAL) != 0) {
      OS.g_object_set(handle, fixed_height_mode, true, 0);
    }
    if (!searchEnabled()) {
      if (OS.GTK_VERSION >= OS.VERSION(2, 6, 5)) {
        OS.gtk_tree_view_set_search_column(handle, -1);
      } else {
        OS.gtk_tree_view_set_enable_search(handle, false);
      }
    }
  }
}
