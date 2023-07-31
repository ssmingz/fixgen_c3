class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    if ((style & (SWT.PUSH | SWT.TOGGLE)) == 0) {
      state |= THEME_BACKGROUND;
    }
    int bits = (((SWT.ARROW | SWT.TOGGLE) | SWT.CHECK) | SWT.RADIO) | SWT.PUSH;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_fixed_set_has_window(fixedHandle, true);
    switch (style & bits) {
      case SWT.ARROW:
        int arrow_type = OS.GTK_ARROW_UP;
        if ((style & SWT.UP) != 0) {
          arrow_type = OS.GTK_ARROW_UP;
        }
        if ((style & SWT.DOWN) != 0) {
          arrow_type = OS.GTK_ARROW_DOWN;
        }
        if ((style & SWT.LEFT) != 0) {
          arrow_type = OS.GTK_ARROW_LEFT;
        }
        if ((style & SWT.RIGHT) != 0) {
          arrow_type = OS.GTK_ARROW_RIGHT;
        }
        handle = OS.gtk_button_new();
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        arrowHandle = OS.gtk_arrow_new(arrow_type, GTK_SHADOW_OUT);
        if (arrowHandle == 0) {
          error(ERROR_NO_HANDLES);
        }
        break;
      case SWT.TOGGLE:
        handle = OS.gtk_toggle_button_new();
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        break;
      case SWT.CHECK:
        handle = OS.gtk_check_button_new();
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        break;
      case SWT.RADIO:
        groupHandle = OS.gtk_radio_button_new(0);
        if (groupHandle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.g_object_ref(groupHandle);
        OS.gtk_object_sink(groupHandle);
        handle = OS.gtk_radio_button_new(OS.gtk_radio_button_get_group(groupHandle));
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        break;
      case SWT.PUSH:
      default:
        handle = OS.gtk_button_new();
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.GTK_WIDGET_SET_FLAGS(handle, GTK_CAN_DEFAULT);
        break;
    }
    if ((style & SWT.ARROW) != 0) {
      OS.gtk_container_add(handle, arrowHandle);
    } else {
      boxHandle = OS.gtk_hbox_new(false, 4);
      if (boxHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      labelHandle = OS.gtk_label_new_with_mnemonic(null);
      if (labelHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      imageHandle = OS.gtk_image_new();
      if (imageHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(handle, boxHandle);
      OS.gtk_container_add(boxHandle, imageHandle);
      OS.gtk_container_add(boxHandle, labelHandle);
      if ((style & SWT.WRAP) != 0) {
        OS.gtk_label_set_line_wrap(labelHandle, true);
        if (OS.GTK_VERSION >= OS.VERSION(2, 10, 0)) {
          OS.gtk_label_set_line_wrap_mode(labelHandle, PANGO_WRAP_WORD_CHAR);
        }
      }
    }
    OS.gtk_container_add(fixedHandle, handle);
    if ((style & SWT.ARROW) != 0) {
      return;
    }
    _setAlignment(style & ((SWT.LEFT | SWT.CENTER) | SWT.RIGHT));
  }
}
