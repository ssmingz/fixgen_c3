class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
      OS.g_signal_connect_closure(handle, activate, display.closures[ACTIVATE], false);
      OS.g_signal_connect_closure(handle, activate, display.closures[ACTIVATE_INVERSE], true);
      OS.g_signal_connect_closure_by_id(
          handle,
          display.signalIds[BUTTON_PRESS_EVENT],
          0,
          display.closures[BUTTON_PRESS_EVENT],
          false);
      OS.g_signal_connect_closure_by_id(
          handle, display.signalIds[FOCUS_OUT_EVENT], 0, display.closures[FOCUS_OUT_EVENT], false);
      OS.g_signal_connect_closure(
          clientHandle, size_allocate, display.closures[SIZE_ALLOCATE], true);
    }
  }
}
