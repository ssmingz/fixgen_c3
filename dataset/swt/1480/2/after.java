class PlaceHold {
  int gtk_realize(int widget) {
    int result = super.gtk_realize(widget);
    int window = gtk_widget_get_window(shellHandle);
    if ((style & SWT.SHELL_TRIM) != SWT.SHELL_TRIM) {
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
    }
    if ((style & SWT.ON_TOP) != 0) {
      OS.gdk_window_set_override_redirect(window, true);
    }
    return result;
  }
}
