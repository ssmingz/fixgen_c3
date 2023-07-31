class PlaceHold {
  void fixZOrder() {
    if ((state & CANVAS) != 0) {
      return;
    }
    int parentHandle = parentingHandle();
    int parentWindow = gtk_widget_get_window(parentHandle);
    if (parentWindow == 0) {
      return;
    }
    int[] userData = new int[1];
    int windowList = OS.gdk_window_get_children(parentWindow);
    if (windowList != 0) {
      int windows = windowList;
      while (windows != 0) {
        int window = OS.g_list_data(windows);
        if (window != redrawWindow) {
          OS.gdk_window_get_user_data(window, userData);
          if ((userData[0] == 0)
              || (OS.G_OBJECT_TYPE(userData[0]) != display.gtk_fixed_get_type())) {
            OS.gdk_window_lower(window);
          }
        }
        windows = OS.g_list_next(windows);
      }
      OS.g_list_free(windowList);
    }
  }
}
