class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    if (labelHandle != 0) {
      OS.g_signal_connect_closure_by_id(
          labelHandle,
          display.signalIds[MNEMONIC_ACTIVATE],
          0,
          display.closures[MNEMONIC_ACTIVATE],
          false);
    }
    OS.g_signal_connect_closure_by_id(
        handle,
        display.signalIds[ENTER_NOTIFY_EVENT],
        0,
        display.closures[ENTER_NOTIFY_EVENT],
        false);
  }
}
