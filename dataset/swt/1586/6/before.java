class PlaceHold {
  void init() {
    if (eventCallback == null) {
      eventCallback = new Callback(getClass(), "eventProc", 3);
      eventProc = eventCallback.getAddress();
      if (eventProc == 0) {
        browser.dispose();
        Mozilla.error(ERROR_NO_MORE_CALLBACKS);
      }
    }
    int list = OS.gtk_container_get_children(embedHandle);
    if (list != 0) {
      mozillaHandle = OS.g_list_data(list);
      OS.g_list_free(list);
      if (mozillaHandle != 0) {
        OS.g_signal_connect(mozillaHandle, event, eventProc, 0);
        OS.g_signal_connect(mozillaHandle, key_press_event, eventProc, STOP_PROPOGATE);
        OS.g_signal_connect(mozillaHandle, key_release_event, eventProc, STOP_PROPOGATE);
        OS.g_signal_connect(mozillaHandle, button_press_event, eventProc, STOP_PROPOGATE);
      }
    }
  }
}
