class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    OS.g_signal_connect(handle, value_changed, windowProc2, VALUE_CHANGED);
  }
}
