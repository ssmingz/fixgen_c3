class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | THEME_BACKGROUND;
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_fixed_set_has_window(fixedHandle, true);
    if ((style & SWT.SEPARATOR) != 0) {
      if ((style & SWT.HORIZONTAL) != 0) {
        handle = OS.gtk_hseparator_new();
      } else {
        handle = OS.gtk_vseparator_new();
      }
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
    } else {
      handle = OS.gtk_hbox_new(false, 0);
      if (handle == 0) {
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
      OS.gtk_container_add(handle, labelHandle);
      OS.gtk_container_add(handle, imageHandle);
    }
    if ((style & SWT.BORDER) != 0) {
      frameHandle = OS.gtk_frame_new(null);
      if (frameHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(fixedHandle, frameHandle);
      OS.gtk_container_add(frameHandle, handle);
      OS.gtk_frame_set_shadow_type(frameHandle, GTK_SHADOW_ETCHED_IN);
    } else {
      OS.gtk_container_add(fixedHandle, handle);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    if ((style & SWT.WRAP) != 0) {
      OS.gtk_label_set_line_wrap(labelHandle, true);
      if (OS.GTK_VERSION >= OS.VERSION(2, 10, 0)) {
        OS.gtk_label_set_line_wrap_mode(labelHandle, PANGO_WRAP_WORD_CHAR);
      }
    }
    setAlignment();
  }
}
