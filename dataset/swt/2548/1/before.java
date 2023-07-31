class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    OS.g_signal_connect(handle, switch_page, windowProc4, SWITCH_PAGE);
  }
}
