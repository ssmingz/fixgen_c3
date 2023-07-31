class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    OS.g_signal_connect_closure(handle, value_changed, display.closures[VALUE_CHANGED], false);
  }
}
