class PlaceHold {
  void updateBar(int selection, int minimum, int maximum) {
    if (!gtk_widget_get_realized(handle)) {
      return;
    }
    double fraction =
        (minimum == maximum) ? 1 : ((double) (selection - minimum)) / (maximum - minimum);
    OS.gtk_progress_bar_set_fraction(handle, fraction);
    long window = paintWindow();
    OS.gdk_window_process_updates(window, false);
    OS.gdk_flush();
  }
}
