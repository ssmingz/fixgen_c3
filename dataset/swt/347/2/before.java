class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    Display display = getDisplay();
    int windowProc2 = display.windowProc2;
    int hAdjustment = OS.gtk_range_get_adjustment(handle);
    OS.gtk_signal_connect(hAdjustment, value_changed, windowProc2, Selection);
  }
}
