class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    if (OS.GTK_VERSION >= OS.VERSION(2, 6, 0)) {
      OS.g_signal_connect(handle, change_value, windowProc5, CHANGE_VALUE);
    } else {
      OS.g_signal_connect(adjustmentHandle, value_changed, windowProc2, VALUE_CHANGED);
    }
  }
}
