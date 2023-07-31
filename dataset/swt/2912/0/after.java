class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    Display display = getDisplay();
    int windowProc2 = display.windowProc2;
    OS.gtk_signal_connect(handle, value_changed, windowProc2, Selection);
  }
}
