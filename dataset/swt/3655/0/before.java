class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    byte[] buffer = new byte[1];
    int bits = ((SWT.CHECK | SWT.RADIO) | SWT.PUSH) | SWT.SEPARATOR;
    switch (style & bits) {
      case SWT.SEPARATOR:
        handle = OS.gtk_separator_menu_item_new();
        break;
      case SWT.RADIO:
        groupHandle = OS.gtk_radio_menu_item_new(0);
        if (groupHandle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.g_object_ref(groupHandle);
        g_object_ref_sink(groupHandle);
        int group = OS.gtk_radio_menu_item_get_group(groupHandle);
        handle = OS.gtk_radio_menu_item_new_with_label(group, buffer);
        break;
      case SWT.CHECK:
        handle = OS.gtk_check_menu_item_new_with_label(buffer);
        break;
      case SWT.PUSH:
      default:
        handle = OS.gtk_image_menu_item_new_with_label(buffer);
        break;
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((style & SWT.SEPARATOR) == 0) {
      int label = OS.gtk_bin_get_child(handle);
      OS.gtk_accel_label_set_accel_widget(label, 0);
    }
    int parentHandle = parent.handle;
    boolean enabled = gtk_widget_get_sensitive(parentHandle);
    if (!enabled) {
      OS.gtk_widget_set_sensitive(parentHandle, true);
    }
    OS.gtk_menu_shell_insert(parentHandle, handle, index);
    if (!enabled) {
      OS.GTK_WIDGET_UNSET_FLAGS(parentHandle, GTK_SENSITIVE);
    }
    OS.gtk_widget_show(handle);
  }
}
