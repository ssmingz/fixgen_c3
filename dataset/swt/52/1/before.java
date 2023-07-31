class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    Display display = getDisplay();
    OS.g_signal_connect(handle, clicked, display.windowProc2, CLICKED);
  }
}
