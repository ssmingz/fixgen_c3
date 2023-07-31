class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    Display display = getDisplay();
    OS.g_signal_connect(handle, value_changed, display.windowProc2, VALUE_CHANGED);
  }
}
