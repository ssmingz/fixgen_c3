class PlaceHold {
  int gtk_button_release_event(int widget, int event) {
    int window = OS.GDK_EVENT_WINDOW(event);
    if (window != OS.gtk_tree_view_get_bin_window(handle)) {
      return 0;
    }
    return super.gtk_button_release_event(widget, event);
  }
}
