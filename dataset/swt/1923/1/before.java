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
  }
}
