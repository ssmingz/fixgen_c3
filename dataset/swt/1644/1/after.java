class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    Display display = getDisplay();
    int hAdjustment = OS.gtk_range_get_adjustment(handle);
    OS.g_signal_connect(hAdjustment, value_changed, display.windowProc2, VALUE_CHANGED);
  }
}
