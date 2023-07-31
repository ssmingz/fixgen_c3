class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | MENU;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    gtk_widget_set_has_window(fixedHandle, true);
    int oldList = OS.gtk_window_list_toplevels();
    if ((style & SWT.READ_ONLY) != 0) {
      handle = OS.gtk_combo_box_new_text();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      cellHandle = OS.gtk_bin_get_child(handle);
      if (cellHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
    } else {
      handle = OS.gtk_combo_box_entry_new_text();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      entryHandle = OS.gtk_bin_get_child(handle);
      if (entryHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
    }
    popupHandle = findPopupHandle(oldList);
    OS.gtk_container_add(fixedHandle, handle);
    textRenderer = OS.gtk_cell_renderer_text_new();
    if (textRenderer == 0) {
      error(ERROR_NO_HANDLES);
    }
    int pad = 0;
    if (OS.GTK_VERSION < OS.VERSION(2, 6, 0)) {
      pad = 1;
    }
    OS.g_object_set(textRenderer, ypad, pad, 0);
    boolean warnings = display.getWarnings();
    display.setWarnings(false);
    OS.gtk_cell_layout_clear(handle);
    display.setWarnings(warnings);
    OS.gtk_cell_layout_pack_start(handle, textRenderer, true);
    OS.gtk_cell_layout_set_attributes(handle, textRenderer, text, 0, 0);
    if (OS.GTK_VERSION < OS.VERSION(2, 8, 0)) {
      OS.gtk_widget_size_request(handle, new GtkRequisition());
    }
    if (popupHandle != 0) {
      findMenuHandle();
    }
    findButtonHandle();
    if (((style & SWT.READ_ONLY) != 0) && (buttonHandle != 0)) {
      OS.GTK_WIDGET_UNSET_FLAGS(buttonHandle, GTK_RECEIVES_DEFAULT);
    }
  }
}
