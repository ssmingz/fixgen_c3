class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    Display display = getDisplay();
    int windowProc4 = display.windowProc4;
    OS.gtk_signal_connect(handle, switch_page, windowProc4, Selection);
  }
}
