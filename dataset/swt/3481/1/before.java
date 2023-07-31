class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    int windowProc2 = display.windowProc2;
    int windowProc3 = display.windowProc3;
    OS.g_signal_connect(listHandle, select_child, windowProc3, SELECT_CHILD);
    OS.g_signal_connect_after(entryHandle, changed, windowProc2, CHANGED);
    OS.g_signal_connect(entryHandle, activate, windowProc2, ACTIVATE);
    int mask =
        (((OS.GDK_POINTER_MOTION_MASK | OS.GDK_BUTTON_PRESS_MASK) | OS.GDK_BUTTON_RELEASE_MASK)
                | OS.GDK_KEY_PRESS_MASK)
            | OS.GDK_KEY_RELEASE_MASK;
    int[] handles = new int[] {arrowHandle, entryHandle, listHandle};
    for (int i = 0; i < handles.length; i++) {
      int handle = handles[i];
      if (handle != 0) {
        OS.gtk_widget_add_events(handle, mask);
        OS.g_signal_connect(handle, button_press_event, windowProc3, BUTTON_PRESS_EVENT);
        OS.g_signal_connect(handle, button_release_event, windowProc3, BUTTON_RELEASE_EVENT);
        OS.g_signal_connect(handle, motion_notify_event, windowProc3, MOTION_NOTIFY_EVENT);
        OS.g_signal_connect(handle, key_press_event, windowProc3, KEY_PRESS_EVENT);
        OS.g_signal_connect(handle, key_release_event, windowProc3, KEY_RELEASE_EVENT);
        OS.g_signal_connect(handle, show_help, windowProc3, SHOW_HELP);
        OS.g_signal_connect(handle, focus_in_event, windowProc3, FOCUS_IN_EVENT);
        OS.g_signal_connect(handle, focus_out_event, windowProc3, FOCUS_OUT_EVENT);
        OS.g_signal_connect_after(handle, button_press_event, windowProc3, -BUTTON_PRESS_EVENT);
        OS.g_signal_connect_after(handle, button_release_event, windowProc3, -BUTTON_RELEASE_EVENT);
        OS.g_signal_connect_after(handle, motion_notify_event, windowProc3, -MOTION_NOTIFY_EVENT);
      }
    }
  }
}
