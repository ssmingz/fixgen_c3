class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
      OS.gtk_widget_set_has_window(fixedHandle, true);
    } else {
      OS.gtk_fixed_set_has_window(fixedHandle, true);
    }
    scrolledHandle = OS.gtk_scrolled_window_new(0, 0);
    if (scrolledHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int[] types = new int[] {OS.G_TYPE_STRING()};
    modelHandle = OS.gtk_list_store_newv(types.length, types);
    if (modelHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    handle = OS.gtk_tree_view_new_with_model(modelHandle);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int textRenderer = OS.gtk_cell_renderer_text_new();
    if (textRenderer == 0) {
      error(ERROR_NO_HANDLES);
    }
    int columnHandle = OS.gtk_tree_view_column_new();
    if (columnHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_tree_view_column_pack_start(columnHandle, textRenderer, true);
    OS.gtk_tree_view_column_add_attribute(columnHandle, textRenderer, text, TEXT_COLUMN);
    OS.gtk_tree_view_column_set_min_width(columnHandle, 0);
    OS.gtk_tree_view_insert_column(handle, columnHandle, index);
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
    if ((getShell().style & SWT.ON_TOP) != 0) {
      if (OS.GTK_VERSION >= OS.VERSION(2, 6, 5)) {
        OS.gtk_tree_view_set_search_column(handle, -1);
      } else {
        OS.gtk_tree_view_set_enable_search(handle, false);
      }
    }
  }
}
