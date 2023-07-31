class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    int hAdjustment = OS.gtk_range_get_adjustment(handle);
    GtkAdjustment adjustment = new GtkAdjustment();
    OS.memmove(adjustment, hAdjustment);
    signal_connect(hAdjustment, "value_changed", Selection, 2);
  }
}
