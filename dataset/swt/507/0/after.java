class PlaceHold {
  int paintWindow() {
    OS.gtk_widget_realize(entryHandle);
    int window = OS.GTK_WIDGET_WINDOW(entryHandle);
    int children = OS.gdk_window_get_children(window);
    if (children != 0) {
      window = OS.g_list_data(children);
    }
    OS.g_list_free(children);
    return window;
  }
}
