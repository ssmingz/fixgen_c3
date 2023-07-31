class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    OS.g_signal_connect_closure_by_id(
        shellHandle,
        display.signalIds[KEY_PRESS_EVENT],
        0,
        display.closures[KEY_PRESS_EVENT],
        false);
    OS.g_signal_connect_closure_by_id(
        shellHandle,
        display.signalIds[WINDOW_STATE_EVENT],
        0,
        display.closures[WINDOW_STATE_EVENT],
        false);
    OS.g_signal_connect_closure_by_id(
        shellHandle, display.signalIds[SIZE_ALLOCATE], 0, display.closures[SIZE_ALLOCATE], false);
    OS.g_signal_connect_closure_by_id(
        shellHandle,
        display.signalIds[CONFIGURE_EVENT],
        0,
        display.closures[CONFIGURE_EVENT],
        false);
    OS.g_signal_connect_closure_by_id(
        shellHandle, display.signalIds[DELETE_EVENT], 0, display.closures[DELETE_EVENT], false);
    OS.g_signal_connect_closure_by_id(
        shellHandle, display.signalIds[MAP_EVENT], 0, shellMapProcClosure, false);
    OS.g_signal_connect_closure_by_id(
        shellHandle,
        display.signalIds[ENTER_NOTIFY_EVENT],
        0,
        display.closures[ENTER_NOTIFY_EVENT],
        false);
    OS.g_signal_connect_closure(shellHandle, move_focus, display.closures[MOVE_FOCUS], false);
    int window = gtk_widget_get_window(shellHandle);
    OS.gdk_window_add_filter(window, filterProc, shellHandle);
    if (isCustomResize()) {
      int mask =
          (((OS.GDK_POINTER_MOTION_MASK | OS.GDK_BUTTON_RELEASE_MASK) | OS.GDK_BUTTON_PRESS_MASK)
                  | OS.GDK_ENTER_NOTIFY_MASK)
              | OS.GDK_LEAVE_NOTIFY_MASK;
      OS.gtk_widget_add_events(shellHandle, mask);
      OS.g_signal_connect_closure_by_id(
          shellHandle, display.signalIds[EXPOSE_EVENT], 0, display.closures[EXPOSE_EVENT], false);
      OS.g_signal_connect_closure_by_id(
          shellHandle,
          display.signalIds[LEAVE_NOTIFY_EVENT],
          0,
          display.closures[LEAVE_NOTIFY_EVENT],
          false);
      OS.g_signal_connect_closure_by_id(
          shellHandle,
          display.signalIds[MOTION_NOTIFY_EVENT],
          0,
          display.closures[MOTION_NOTIFY_EVENT],
          false);
      OS.g_signal_connect_closure_by_id(
          shellHandle,
          display.signalIds[BUTTON_PRESS_EVENT],
          0,
          display.closures[BUTTON_PRESS_EVENT],
          false);
    }
  }
}
