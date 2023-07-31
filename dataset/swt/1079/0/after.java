class PlaceHold {
  int paintWindow() {
    int childHandle = (entryHandle != 0) ? entryHandle : handle;
    OS.gtk_widget_realize(childHandle);
    int window = OS.GTK_WIDGET_WINDOW(childHandle);
    if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
      if ((style & SWT.READ_ONLY) != 0) {
        return window;
      }
    }
    int children = OS.gdk_window_get_children(window);
    if (children != 0) {
      window = OS.g_list_data(children);
    }
    OS.g_list_free(children);
    return window;
  }
}
