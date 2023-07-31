class PlaceHold {
  long gtk_motion_notify_event(long widget, long event) {
    GdkEventMotion gdkEvent = new GdkEventMotion();
    OS.memmove(gdkEvent, event, sizeof);
    if ((this == display.currentControl) && (hooks(MouseHover) || filters(MouseHover))) {
      display.addMouseHoverTimeout(handle);
    }
    double x = gdkEvent.x_root;
    double y = gdkEvent.y_root;
    int state = gdkEvent.state;
    if (gdkEvent.is_hint != 0) {
      int[] pointer_x = new int[1];
      int[] pointer_y = new int[1];
      int[] mask = new int[1];
      long window = eventWindow();
      OS.gdk_window_get_pointer(window, pointer_x, pointer_y, mask);
      x = pointer_x[0];
      y = pointer_y[0];
      state = mask[0];
    }
    int result =
        (sendMouseEvent(MouseMove, 0, gdkEvent.time, x, y, gdkEvent.is_hint != 0, state)) ? 0 : 1;
    return result;
  }
}
