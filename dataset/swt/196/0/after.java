class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    signal_connect(listHandle, "select_child", Selection, 3);
    signal_connect_after(entryHandle, "changed", Modify, 2);
    int mask =
        (((OS.GDK_POINTER_MOTION_MASK | OS.GDK_BUTTON_PRESS_MASK) | OS.GDK_BUTTON_RELEASE_MASK)
                | OS.GDK_KEY_PRESS_MASK)
            | OS.GDK_KEY_RELEASE_MASK;
    int[] handles = new int[] {entryHandle, listHandle};
    for (int i = 0; i < handles.length; i++) {
      int handle = handles[i];
      if (!OS.GTK_WIDGET_NO_WINDOW(handle)) {
        OS.gtk_widget_add_events(handle, mask);
      }
      signal_connect_after(handle, "event-after", MouseDown, 3);
      signal_connect_after(handle, "motion_notify_event", MouseMove, 3);
    }
  }
}
