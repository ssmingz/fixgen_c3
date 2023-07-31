class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    if ((style & SWT.SEPARATOR) == 0) {
      boxHandle =
          ((parent.style & SWT.RIGHT) != 0) ? OS.gtk_hbox_new(false, 0) : OS.gtk_vbox_new(false, 0);
      if (boxHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      labelHandle = OS.gtk_label_new_with_mnemonic(null);
      if (labelHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      Display display = getDisplay();
      pixmapHandle = OS.gtk_pixmap_new(display.nullPixmap, 0);
      if (pixmapHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(boxHandle, pixmapHandle);
      OS.gtk_container_add(boxHandle, labelHandle);
      OS.gtk_widget_show(boxHandle);
    }
    int bits = (((SWT.SEPARATOR | SWT.RADIO) | SWT.CHECK) | SWT.PUSH) | SWT.DROP_DOWN;
    switch (style & bits) {
      case SWT.SEPARATOR:
        handle = OS.gtk_hbox_new(false, 0);
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        boolean isVertical = (parent.style & SWT.VERTICAL) != 0;
        separatorHandle = (isVertical) ? OS.gtk_hseparator_new() : OS.gtk_vseparator_new();
        if (separatorHandle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.gtk_widget_set_size_request(separatorHandle, isVertical ? 15 : 6, isVertical ? 6 : 15);
        OS.gtk_container_add(handle, separatorHandle);
        OS.gtk_widget_show(separatorHandle);
        break;
      case SWT.DROP_DOWN:
        handle = OS.gtk_button_new();
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        int arrowBoxHandle = OS.gtk_hbox_new(false, 0);
        if (arrowBoxHandle == 0) {
          error(ERROR_NO_HANDLES);
        }
        arrowHandle = OS.gtk_arrow_new(GTK_ARROW_DOWN, GTK_SHADOW_NONE);
        if (arrowHandle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.gtk_widget_set_size_request(arrowHandle, 8, 6);
        OS.gtk_container_add(handle, arrowBoxHandle);
        OS.gtk_container_add(arrowBoxHandle, boxHandle);
        OS.gtk_container_add(arrowBoxHandle, arrowHandle);
        OS.gtk_widget_show(arrowBoxHandle);
        OS.gtk_widget_show(arrowHandle);
        break;
      case SWT.RADIO:
        handle = OS.gtk_radio_button_new(0);
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.gtk_toggle_button_set_mode(handle, false);
        OS.gtk_container_add(handle, boxHandle);
        break;
      case SWT.CHECK:
        handle = OS.gtk_toggle_button_new();
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.gtk_toggle_button_set_mode(handle, false);
        OS.gtk_container_add(handle, boxHandle);
        break;
      case SWT.PUSH:
      default:
        handle = OS.gtk_button_new();
        if (handle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.gtk_container_add(handle, boxHandle);
        break;
    }
    if ((style & SWT.SEPARATOR) == 0) {
      int[] relief = new int[1];
      OS.gtk_widget_style_get(parent.handle, button_relief, relief, 0);
      OS.gtk_button_set_relief(handle, relief[0]);
      OS.GTK_WIDGET_UNSET_FLAGS(handle, GTK_CAN_FOCUS);
    }
    OS.gtk_widget_show(handle);
    OS.gtk_toolbar_insert_widget(parent.handle, handle, null, null, index);
  }
}
