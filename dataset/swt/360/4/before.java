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
    int[] types = new int[(style & SWT.CHECK) != 0 ? 8 : 6];
    types[TEXT_COLUMN] = OS.G_TYPE_STRING();
    types[PIXBUF_COLUMN] = OS.GDK_TYPE_PIXBUF();
    types[FOREGROUND_COLUMN] = OS.GDK_TYPE_COLOR();
    types[BACKGROUND_COLUMN] = OS.GDK_TYPE_COLOR();
    types[FONT_COLUMN] = OS.PANGO_TYPE_FONT_DESCRIPTION();
    types[ID_COLUMN] = OS.G_TYPE_INT();
    if ((style & SWT.CHECK) != 0) {
      types[CHECKED_COLUMN] = OS.G_TYPE_BOOLEAN();
      types[GRAYED_COLUMN] = OS.G_TYPE_BOOLEAN();
    }
    modelHandle = OS.gtk_tree_store_newv(types.length, types);
    if (modelHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    handle = OS.gtk_tree_view_new_with_model(modelHandle);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    columnHandle = OS.gtk_tree_view_column_new();
    if (columnHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.g_object_ref(columnHandle);
    if ((style & SWT.CHECK) != 0) {
      checkRenderer = OS.gtk_cell_renderer_toggle_new();
      if (checkRenderer == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_tree_view_column_pack_start(columnHandle, checkRenderer, false);
      OS.gtk_tree_view_column_add_attribute(columnHandle, checkRenderer, "active", CHECKED_COLUMN);
      if ((OS.gtk_major_version() > 2)
          || ((OS.gtk_major_version() == 2) && (OS.gtk_minor_version() >= 2))) {
        OS.gtk_tree_view_column_add_attribute(
            columnHandle, checkRenderer, "inconsistent", GRAYED_COLUMN);
      }
    }
    pixbufRenderer = OS.gtk_cell_renderer_pixbuf_new();
    if (pixbufRenderer == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_tree_view_column_pack_start(columnHandle, pixbufRenderer, false);
    OS.gtk_tree_view_column_add_attribute(columnHandle, pixbufRenderer, "pixbuf", PIXBUF_COLUMN);
    if ((style & SWT.CHECK) != 0) {
      OS.g_object_set(pixbufRenderer, mode, GTK_CELL_RENDERER_MODE_ACTIVATABLE, 0);
    }
    textRenderer = OS.gtk_cell_renderer_text_new();
    if (textRenderer == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_tree_view_column_pack_start(columnHandle, textRenderer, true);
    OS.gtk_tree_view_column_add_attribute(columnHandle, textRenderer, "text", TEXT_COLUMN);
    OS.gtk_tree_view_column_add_attribute(
        columnHandle, textRenderer, "foreground-gdk", FOREGROUND_COLUMN);
    OS.gtk_tree_view_column_add_attribute(
        columnHandle, textRenderer, "background-gdk", BACKGROUND_COLUMN);
    OS.gtk_tree_view_column_add_attribute(columnHandle, textRenderer, "font-desc", FONT_COLUMN);
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
  }
}
