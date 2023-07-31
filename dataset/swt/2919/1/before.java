class PlaceHold {
  public Control getCursorControl() {
    checkDevice();
    int[] x = new int[1];
    int[] y = new int[1];
    long handle = 0;
    long[] user_data = new long[1];
    long window = gdk_device_get_window_at_position(x, y);
    if (window != 0) {
      OS.gdk_window_get_user_data(window, user_data);
      handle = user_data[0];
    } else {
      if (!OS.GDK_WINDOWING_X11()) {
        return null;
      }
      OS.gdk_error_trap_push();
      int[] unusedInt = new int[1];
      long[] unusedPtr = new long[1];
      long[] buffer = new long[1];
      long xWindow;
      long xParent = OS.XDefaultRootWindow(xDisplay);
      do {
        if (OS.XQueryPointer(
                xDisplay, xParent, unusedPtr, buffer, unusedInt, unusedInt, unusedInt, unusedInt,
                unusedInt)
            == 0) {
          handle = 0;
          break;
        }
        if ((xWindow = buffer[0]) != 0) {
          xParent = xWindow;
          long gdkWindow = 0;
          if (OS.GTK_VERSION >= OS.VERSION(2, 24, 0)) {
            gdkWindow = OS.gdk_x11_window_lookup_for_display(OS.gdk_display_get_default(), xWindow);
          } else {
            gdkWindow = OS.gdk_window_lookup(xWindow);
          }
          if (gdkWindow != 0) {
            OS.gdk_window_get_user_data(gdkWindow, user_data);
            if (user_data[0] != 0) {
              handle = user_data[0];
            }
          }
        }
      } while (xWindow != 0);
      OS.gdk_error_trap_pop();
    }
    if (handle == 0) {
      return null;
    }
    do {
      Widget widget = getWidget(handle);
      if ((widget != null) && (widget instanceof Control)) {
        Control control = ((Control) (widget));
        if (control.isEnabled()) {
          return control;
        }
      }
    } while ((handle = OS.gtk_widget_get_parent(handle)) != 0);
    return null;
  }
}
