class PlaceHold {
  void update(boolean all) {
    if (!OS.GTK_WIDGET_VISIBLE(topHandle())) {
      return;
    }
    if ((OS.GTK_WIDGET_FLAGS(handle) & OS.GTK_REALIZED) == 0) {
      return;
    }
    int window = paintWindow();
    display.flushExposes(window, all);
    OS.gdk_window_process_updates(window, all);
  }
}
