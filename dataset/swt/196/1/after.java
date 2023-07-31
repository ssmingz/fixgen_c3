class PlaceHold {
  void hookEvents() {
    int eventHandle = eventHandle();
    signal_connect_after(eventHandle, "expose_event", Paint, 3);
    if (true || (!OS.GTK_WIDGET_NO_WINDOW(eventHandle))) {
      int mask =
          ((((((OS.GDK_POINTER_MOTION_MASK | OS.GDK_BUTTON_PRESS_MASK) | OS.GDK_BUTTON_RELEASE_MASK)
                              | OS.GDK_ENTER_NOTIFY_MASK)
                          | OS.GDK_LEAVE_NOTIFY_MASK)
                      | OS.GDK_KEY_PRESS_MASK)
                  | OS.GDK_KEY_RELEASE_MASK)
              | OS.GDK_FOCUS_CHANGE_MASK;
      OS.gtk_widget_add_events(eventHandle, mask);
    }
    signal_connect_after(eventHandle, "event-after", MouseDown, 3);
    signal_connect_after(eventHandle, "motion_notify_event", MouseMove, 3);
    signal_connect_after(eventHandle, "enter_notify_event", MouseEnter, 3);
    signal_connect_after(eventHandle, "leave_notify_event", MouseExit, 3);
    signal_connect_after(eventHandle, "focus_in_event", FocusIn, 3);
    signal_connect_after(eventHandle, "focus_out_event", FocusOut, 3);
  }
}
