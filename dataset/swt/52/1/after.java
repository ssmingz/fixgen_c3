class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    Display display = getDisplay();
    OS.g_signal_connect(handle, clicked, display.windowProc2, CLICKED);
    if (labelHandle != 0) {
      OS.g_signal_connect(labelHandle, mnemonic_activate, display.windowProc3, MNEMONIC_ACTIVATE);
    }
  }
}
