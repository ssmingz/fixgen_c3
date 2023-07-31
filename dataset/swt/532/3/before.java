class PlaceHold {
  void update(boolean all, boolean flush) {
    if (!gtk_widget_get_visible(topHandle())) {
      return;
    }
    if (!gtk_widget_get_realized(handle)) {
      return;
    }
    int window = paintWindow();
    if (flush) {
      display.flushExposes(window, all);
    }
    OS.gdk_window_process_updates(window, all);
    OS.gdk_flush();
  }
}
