class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    int windowProc2 = display.windowProc2;
    int windowProc3 = display.windowProc3;
    OS.g_signal_connect(handle, clicked, windowProc2, CLICKED);
    OS.g_signal_connect(handle, enter_notify_event, windowProc3, ENTER_NOTIFY_EVENT);
    OS.g_signal_connect(handle, leave_notify_event, windowProc3, LEAVE_NOTIFY_EVENT);
    int mask =
        (((((((OS.GDK_EXPOSURE_MASK | OS.GDK_POINTER_MOTION_MASK) | OS.GDK_BUTTON_PRESS_MASK)
                                | OS.GDK_BUTTON_RELEASE_MASK)
                            | OS.GDK_ENTER_NOTIFY_MASK)
                        | OS.GDK_LEAVE_NOTIFY_MASK)
                    | OS.GDK_KEY_PRESS_MASK)
                | OS.GDK_KEY_RELEASE_MASK)
            | OS.GDK_FOCUS_CHANGE_MASK;
    OS.gtk_widget_add_events(handle, mask);
    OS.g_signal_connect(handle, button_press_event, windowProc3, BUTTON_PRESS_EVENT);
    OS.g_signal_connect(handle, button_release_event, windowProc3, BUTTON_RELEASE_EVENT);
    OS.g_signal_connect(handle, event_after, windowProc3, EVENT_AFTER);
  }
}
