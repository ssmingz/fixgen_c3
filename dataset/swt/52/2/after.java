class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    OS.g_signal_connect(handle, clicked, windowProc2, CLICKED);
    if (buttonHandle != 0) {
      OS.g_signal_connect(buttonHandle, size_allocate, windowProc3, SIZE_ALLOCATE);
    }
    if (labelHandle != 0) {
      OS.g_signal_connect(labelHandle, mnemonic_activate, windowProc3, MNEMONIC_ACTIVATE);
    }
  }
}
