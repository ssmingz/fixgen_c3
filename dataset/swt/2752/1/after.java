class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | CANVAS;
    int type = OS.GTK_WINDOW_TOPLEVEL;
    if ((style & SWT.ON_TOP) != 0) {
      type = OS.GTK_WINDOW_POPUP;
    }
    shellHandle = OS.gtk_window_new(type);
    if (shellHandle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (parent != null) {
      OS.gtk_window_set_transient_for(shellHandle, parent.topHandle());
      OS.gtk_window_set_destroy_with_parent(shellHandle, true);
    }
    OS.gtk_widget_set_size_request(shellHandle, 0, 0);
    OS.gtk_window_set_resizable(shellHandle, true);
    createHandle(index, shellHandle, true);
    OS.gtk_widget_realize(shellHandle);
    int window = OS.GTK_WIDGET_WINDOW(shellHandle);
    int decorations = 0;
    if ((style & SWT.NO_TRIM) == 0) {
      if ((style & SWT.MIN) != 0) {
        decorations |= OS.GDK_DECOR_MINIMIZE;
      }
      if ((style & SWT.MAX) != 0) {
        decorations |= OS.GDK_DECOR_MAXIMIZE;
      }
      if ((style & SWT.RESIZE) != 0) {
        decorations |= OS.GDK_DECOR_RESIZEH;
      }
      if ((style & SWT.BORDER) != 0) {
        decorations |= OS.GDK_DECOR_BORDER;
      }
      if ((style & SWT.MENU) != 0) {
        decorations |= OS.GDK_DECOR_MENU;
      }
      if ((style & SWT.TITLE) != 0) {
        decorations |= OS.GDK_DECOR_TITLE;
      }
      if ((style & SWT.RESIZE) != 0) {
        decorations |= OS.GDK_DECOR_BORDER;
      }
    }
    OS.gdk_window_set_decorations(window, decorations);
    OS.gtk_window_set_title(shellHandle, new byte[1]);
    if ((style & SWT.ON_TOP) != 0) {
      OS.gdk_window_set_override_redirect(window, true);
    }
    if ((style & ((SWT.NO_TRIM | SWT.BORDER) | SWT.RESIZE)) == 0) {
      OS.gtk_container_set_border_width(shellHandle, 1);
      GdkColor color = new GdkColor();
      OS.gtk_style_get_black(OS.gtk_widget_get_style(shellHandle), color);
      OS.gtk_widget_modify_bg(shellHandle, 0, color);
    }
    int bits = (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL;
    boolean modal = ((style & bits) != 0) || ((parent != null) && ((parent.style & bits) != 0));
    OS.gtk_window_set_modal(shellHandle, modal);
  }
}
