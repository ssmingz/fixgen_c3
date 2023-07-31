class PlaceHold {
  int checkIfEventProc(int display, int xEvent, int userData) {
    int type = OS.X_EVENT_TYPE(xEvent);
    switch (type) {
      case OS.VisibilityNotify:
        if (OS.GTK_VERSION >= OS.VERSION(2, 17, 11)) {
          return 0;
        }
      case OS.Expose:
      case OS.GraphicsExpose:
        break;
      default:
        return 0;
    }
    int window = OS.gdk_window_lookup(OS.X_EVENT_WINDOW(xEvent));
    if (window == 0) {
      return 0;
    }
    if (flushWindow != 0) {
      if (flushAll) {
        int tempWindow = window;
        do {
          if (tempWindow == flushWindow) {
            break;
          }
        } while ((tempWindow = OS.gdk_window_get_parent(tempWindow)) != 0);
        if (tempWindow != flushWindow) {
          return 0;
        }
      } else if (window != flushWindow) {
        return 0;
      }
    }
    OS.memmove(exposeEvent, xEvent, sizeof);
    switch (type) {
      case OS.Expose:
      case OS.GraphicsExpose:
        {
          flushRect.x = exposeEvent.x;
          flushRect.y = exposeEvent.y;
          flushRect.width = exposeEvent.width;
          flushRect.height = exposeEvent.height;
          OS.gdk_window_invalidate_rect(window, flushRect, true);
          exposeEvent.type = -1;
          OS.memmove(xEvent, exposeEvent, sizeof);
          break;
        }
      case OS.VisibilityNotify:
        {
          OS.memmove(visibilityEvent, xEvent, sizeof);
          OS.gdk_window_get_user_data(window, flushData);
          int handle = flushData[0];
          Widget widget = (handle != 0) ? getWidget(handle) : null;
          if ((widget != null) && (widget instanceof Control)) {
            Control control = ((Control) (widget));
            if (window == control.paintWindow()) {
              if (visibilityEvent.state == OS.VisibilityFullyObscured) {
                control.state |= Widget.OBSCURED;
              } else {
                control.state &= ~Widget.OBSCURED;
              }
            }
          }
          break;
        }
    }
    return 0;
  }
}
