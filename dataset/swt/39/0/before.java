class PlaceHold {
  int gtk_motion_notify_event(int widget, int event) {
    if (widget == shellHandle) {
      if (isCustomResize()) {
        GdkEventMotion gdkEvent = new GdkEventMotion();
        OS.memmove(gdkEvent, event, sizeof);
        if ((gdkEvent.state & OS.GDK_BUTTON1_MASK) != 0) {
          int border = OS.gtk_container_get_border_width(shellHandle);
          int dx = ((int) (gdkEvent.x_root - display.resizeLocationX));
          int dy = ((int) (gdkEvent.y_root - display.resizeLocationY));
          int x = display.resizeBoundsX;
          int y = display.resizeBoundsY;
          int width = display.resizeBoundsWidth;
          int height = display.resizeBoundsHeight;
          int newWidth = Math.max(width - dx, Math.max(minWidth, border + border));
          int newHeight = Math.max(height - dy, Math.max(minHeight, border + border));
          switch (display.resizeMode) {
            case OS.GDK_LEFT_SIDE:
              x += width - newWidth;
              width = newWidth;
              break;
            case OS.GDK_TOP_LEFT_CORNER:
              x += width - newWidth;
              width = newWidth;
              y += height - newHeight;
              height = newHeight;
              break;
            case OS.GDK_TOP_SIDE:
              y += height - newHeight;
              height = newHeight;
              break;
            case OS.GDK_TOP_RIGHT_CORNER:
              width = Math.max(width + dx, Math.max(minWidth, border + border));
              y += height - newHeight;
              height = newHeight;
              break;
            case OS.GDK_RIGHT_SIDE:
              width = Math.max(width + dx, Math.max(minWidth, border + border));
              break;
            case OS.GDK_BOTTOM_RIGHT_CORNER:
              width = Math.max(width + dx, Math.max(minWidth, border + border));
              height = Math.max(height + dy, Math.max(minHeight, border + border));
              break;
            case OS.GDK_BOTTOM_SIDE:
              height = Math.max(height + dy, Math.max(minHeight, border + border));
              break;
            case OS.GDK_BOTTOM_LEFT_CORNER:
              x += width - newWidth;
              width = newWidth;
              height = Math.max(height + dy, Math.max(minHeight, border + border));
              break;
          }
          if ((x != display.resizeBoundsX) || (y != display.resizeBoundsY)) {
            OS.gdk_window_move_resize(OS.GTK_WIDGET_WINDOW(shellHandle), x, y, width, height);
          } else {
            OS.gtk_window_resize(shellHandle, width, height);
          }
        } else {
          int mode = getResizeMode(gdkEvent.x, gdkEvent.y);
          if (mode != display.resizeMode) {
            int window = OS.GTK_WIDGET_WINDOW(shellHandle);
            int cursor = OS.gdk_cursor_new(mode);
            OS.gdk_window_set_cursor(window, cursor);
            OS.gdk_cursor_destroy(cursor);
            display.resizeMode = mode;
          }
        }
      }
      return 0;
    }
    return super.gtk_motion_notify_event(widget, event);
  }
}
