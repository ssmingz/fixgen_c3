class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    OS.g_signal_connect(adjustmentHandle, value_changed, windowProc2, VALUE_CHANGED);
  }
}
