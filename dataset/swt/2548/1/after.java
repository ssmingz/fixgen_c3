class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    OS.g_signal_connect_closure(handle, switch_page, display.closures[SWITCH_PAGE], false);
  }
}
