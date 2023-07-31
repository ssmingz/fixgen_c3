class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | MENU;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_fixed_set_has_window(fixedHandle, true);
    if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
      if ((style & SWT.READ_ONLY) != 0) {
        handle = OS.gtk_combo_box_new_text();
        if (handle == 0) {
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
      OS.gtk_container_add(fixedHandle, handle);
      textRenderer = OS.gtk_cell_renderer_text_new();
      if (textRenderer == 0) {
        error(ERROR_NO_HANDLES);
      }
      boolean warnings = display.getWarnings();
      display.setWarnings(false);
      OS.gtk_cell_layout_clear(handle);
      display.setWarnings(warnings);
      OS.gtk_cell_layout_pack_start(handle, textRenderer, true);
      OS.gtk_cell_layout_set_attributes(handle, textRenderer, text, 0, 0);
      OS.gtk_container_forall(handle, allChildrenProc, 0);
      if (display.allChildren != 0) {
        int list = display.allChildren;
        while (list != 0) {
          int widget = OS.g_list_data(list);
          if (OS.GTK_IS_BUTTON(widget)) {
            buttonHandle = widget;
            break;
          }
          list = OS.g_list_next(list);
        }
        OS.g_list_free(allChildren);
        display.allChildren = 0;
      }
    } else {
      handle = OS.gtk_combo_new();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(fixedHandle, handle);
      GtkCombo combo = new GtkCombo();
      OS.memmove(combo, handle);
      entryHandle = combo.entry;
      listHandle = combo.list;
      int list = OS.gtk_container_get_children(handle);
      if (list != 0) {
        int i = 0;
        int count = OS.g_list_length(list);
        while (i < count) {
          int childHandle = OS.g_list_nth_data(list, i);
          if ((childHandle != entryHandle) && (childHandle != listHandle)) {
            buttonHandle = childHandle;
            break;
          }
          i++;
        }
        OS.g_list_free(list);
      }
      boolean editable = (style & SWT.READ_ONLY) == 0;
      OS.gtk_editable_set_editable(entryHandle, editable);
      OS.gtk_combo_disable_activate(handle);
      OS.gtk_combo_set_case_sensitive(handle, true);
    }
  }
}
