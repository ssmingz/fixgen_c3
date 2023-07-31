class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int bits = (((SWT.SEPARATOR | SWT.RADIO) | SWT.CHECK) | SWT.PUSH) | SWT.DROP_DOWN;
    if ((style & SWT.SEPARATOR) == 0) {
      labelHandle = OS.gtk_label_new_with_mnemonic(null);
      if (labelHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      imageHandle = OS.gtk_image_new_from_pixbuf(0);
      if (imageHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
    }
    switch (style & bits) {
      case SWT.SEPARATOR:
        handle = OS.gtk_separator_tool_item_new();
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.gtk_separator_tool_item_set_draw(handle, true);
        break;
      case SWT.DROP_DOWN:
        if (OS.GTK_VERSION >= OS.VERSION(2, 6, 0)) {
          handle = OS.gtk_menu_tool_button_new(0, null);
          if (handle == 0) {
            error(ERROR_NO_HANDLES);
          }
          int child = OS.gtk_bin_get_child(handle);
          int list = OS.gtk_container_get_children(child);
          arrowHandle = OS.g_list_nth_data(list, 1);
          OS.gtk_widget_set_sensitive(arrowHandle, true);
          OS.gtk_widget_set_size_request(OS.gtk_bin_get_child(arrowHandle), 8, 6);
        } else {
          handle = OS.gtk_tool_button_new(0, null);
          if (handle == 0) {
            error(ERROR_NO_HANDLES);
          }
          arrowBoxHandle = OS.gtk_hbox_new(false, 0);
          if (arrowBoxHandle == 0) {
            error(ERROR_NO_HANDLES);
          }
          arrowHandle = OS.gtk_arrow_new(GTK_ARROW_DOWN, GTK_SHADOW_NONE);
          if (arrowHandle == 0) {
            error(ERROR_NO_HANDLES);
          }
          OS.gtk_widget_set_size_request(arrowHandle, 8, 6);
          OS.gtk_container_add(arrowBoxHandle, labelHandle);
          OS.gtk_container_add(arrowBoxHandle, arrowHandle);
          OS.gtk_tool_item_set_is_important(handle, true);
        }
        break;
      case SWT.RADIO:
      case SWT.CHECK:
        handle = OS.gtk_toggle_tool_button_new();
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        break;
      case SWT.PUSH:
      default:
        handle = OS.gtk_tool_button_new(0, null);
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        break;
    }
    if (labelHandle != 0) {
      OS.gtk_tool_button_set_label_widget(handle, labelHandle);
    }
    if (imageHandle != 0) {
      OS.gtk_tool_button_set_icon_widget(handle, imageHandle);
    }
    if ((parent.state & FOREGROUND) != 0) {
      setForegroundColor(parent.getForegroundColor());
    }
    if ((parent.state & FONT) != 0) {
      setFontDescription(parent.getFontDescription());
    }
    if ((parent.style & SWT.RIGHT) != 0) {
      OS.gtk_tool_item_set_is_important(handle, true);
    }
    if ((style & SWT.SEPARATOR) == 0) {
      OS.gtk_tool_button_set_use_underline(handle, true);
    }
  }
}
