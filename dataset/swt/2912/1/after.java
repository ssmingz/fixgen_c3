class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    Display display = getDisplay();
    int windowProc2 = display.windowProc2;
    OS.gtk_signal_connect(handle, clicked, windowProc2, Selection);
  }
}
