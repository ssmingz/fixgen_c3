class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | MENU;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_fixed_set_has_window(fixedHandle, true);
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
          arrowHandle = childHandle;
          break;
        }
        i++;
      }
      OS.g_list_free(list);
    }
    boolean editable = (style & SWT.READ_ONLY) == 0;
    OS.gtk_editable_set_editable(entryHandle, editable);
    OS.gtk_entry_set_activates_default(entryHandle, true);
    OS.gtk_combo_disable_activate(handle);
    OS.gtk_combo_set_case_sensitive(handle, true);
  }
}
