class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    int shellMapProc = display.shellMapProc;
    int windowProc3 = display.windowProc3;
    OS.g_signal_connect(shellHandle, unmap_event, windowProc3, UNMAP_EVENT);
    OS.g_signal_connect(shellHandle, window_state_event, windowProc3, WINDOW_STATE_EVENT);
    OS.g_signal_connect(shellHandle, size_allocate, windowProc3, SIZE_ALLOCATE);
    OS.g_signal_connect(shellHandle, configure_event, windowProc3, CONFIGURE_EVENT);
    OS.g_signal_connect(shellHandle, delete_event, windowProc3, DELETE_EVENT);
    OS.g_signal_connect(shellHandle, focus_in_event, windowProc3, FOCUS_IN_EVENT);
    OS.g_signal_connect(shellHandle, focus_out_event, windowProc3, FOCUS_OUT_EVENT);
    OS.g_signal_connect(shellHandle, map_event, shellMapProc, 0);
    OS.g_signal_connect(shellHandle, enter_notify_event, windowProc3, ENTER_NOTIFY_EVENT);
    if (OS.GDK_WINDOWING_X11()) {
      int window = OS.GTK_WIDGET_WINDOW(shellHandle);
      OS.gdk_window_add_filter(window, filterProc, shellHandle);
    }
  }
}
