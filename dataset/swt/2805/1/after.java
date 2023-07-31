class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | CANVAS;
    if (handle == 0) {
      int type = OS.GTK_WINDOW_TOPLEVEL;
      if ((style & SWT.ON_TOP) != 0) {
        type = OS.GTK_WINDOW_POPUP;
      }
      shellHandle = OS.gtk_window_new(type);
    } else {
      shellHandle = OS.gtk_plug_new(handle);
    }
    if (shellHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if (parent != null) {
      OS.gtk_window_set_transient_for(shellHandle, parent.topHandle());
      OS.gtk_window_set_destroy_with_parent(shellHandle, true);
      if (!isUndecorated()) {
        OS.gtk_window_set_type_hint(shellHandle, GDK_WINDOW_TYPE_HINT_DIALOG);
      }
    }
    if ((style & SWT.RESIZE) != 0) {
      OS.gtk_widget_set_size_request(shellHandle, 0, 0);
      OS.gtk_window_set_resizable(shellHandle, true);
    } else {
      OS.gtk_window_set_resizable(shellHandle, false);
    }
    vboxHandle = OS.gtk_vbox_new(false, 0);
    if (vboxHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    createHandle(index, false, true);
    OS.gtk_container_add(vboxHandle, scrolledHandle);
    OS.gtk_box_set_child_packing(vboxHandle, scrolledHandle, true, true, 0, GTK_PACK_END);
    OS.gtk_window_set_title(shellHandle, new byte[1]);
    if ((style & ((SWT.NO_TRIM | SWT.BORDER) | SWT.RESIZE)) == 0) {
      OS.gtk_container_set_border_width(shellHandle, 1);
      GdkColor color = new GdkColor();
      OS.gtk_style_get_black(OS.gtk_widget_get_style(shellHandle), color);
      OS.gtk_widget_modify_bg(shellHandle, GTK_STATE_NORMAL, color);
    }
    int bits = (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL;
    boolean modal = (style & bits) != 0;
    if ((style & SWT.ON_TOP) == 0) {
      modal |= (parent != null) && ((parent.style & bits) != 0);
    }
    OS.gtk_window_set_modal(shellHandle, modal);
    OS.gtk_widget_realize(shellHandle);
  }
}
