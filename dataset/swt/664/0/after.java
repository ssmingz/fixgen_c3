class PlaceHold {
  int gtk_map_event(int widget, int event) {
    if (enableWindow != 0) {
      OS.gdk_window_raise(enableWindow);
    }
    minimized = false;
    sendEvent(Deiconify);
    return 0;
  }
}
