class PlaceHold {
  public boolean open() {
    checkWidget();
    window = OS.gdk_get_default_root_window();
    if (parent != null) {
      window = gtk_widget_get_window(parent.paintHandle());
    }
    if (window == 0) {
      return false;
    }
    cancelled = false;
    tracking = true;
    if (!OS.GTK3) {
      update();
      drawRectangles(rectangles);
    }
    int[] oldX = new int[1];
    int[] oldY = new int[1];
    int[] state = new int[1];
    gdk_window_get_device_position(window, oldX, oldY, state);
    int vStyle = style & (SWT.UP | SWT.DOWN);
    if ((vStyle == SWT.UP) || (vStyle == SWT.DOWN)) {
      cursorOrientation |= vStyle;
    }
    int hStyle = style & (SWT.LEFT | SWT.RIGHT);
    if ((hStyle == SWT.LEFT) || (hStyle == SWT.RIGHT)) {
      cursorOrientation |= hStyle;
    }
    int mask = (OS.GDK_BUTTON1_MASK | OS.GDK_BUTTON2_MASK) | OS.GDK_BUTTON3_MASK;
    boolean mouseDown = (state[0] & mask) != 0;
    if (!mouseDown) {
      Point cursorPos = null;
      if ((style & SWT.RESIZE) != 0) {
        cursorPos = adjustResizeCursor();
      } else {
        cursorPos = adjustMoveCursor();
      }
      if (cursorPos != null) {
        oldX[0] = cursorPos.x;
        oldY[0] = cursorPos.y;
      }
    }
    this.oldX = oldX[0];
    this.oldY = oldY[0];
    grabbed = grab();
    lastCursor = (this.cursor != null) ? this.cursor.handle : 0;
    if (OS.GTK3) {
      overlay = OS.gtk_window_new(GTK_WINDOW_POPUP);
      OS.gtk_window_set_skip_taskbar_hint(overlay, true);
      OS.gtk_window_set_title(overlay, new byte[1]);
      OS.gtk_widget_realize(overlay);
      long overlayWindow = OS.gtk_widget_get_window(overlay);
      OS.gdk_window_set_override_redirect(overlayWindow, true);
      OS.gtk_widget_override_background_color(overlay, GTK_STATE_FLAG_NORMAL, new GdkRGBA());
      long region = OS.gdk_region_new();
      OS.gtk_widget_shape_combine_region(overlay, region);
      OS.gtk_widget_input_shape_combine_region(overlay, region);
      OS.gdk_region_destroy(region);
      Rectangle bounds = display.getBounds();
      OS.gtk_window_move(overlay, bounds.x, bounds.y);
      OS.gtk_window_resize(overlay, bounds.width, bounds.height);
      OS.gtk_widget_show(overlay);
    }
    Display display = this.display;
    Tracker oldTracker = display.tracker;
    display.tracker = this;
    try {
      while (tracking) {
        if ((parent != null) && parent.isDisposed()) {
          break;
        }
        display.runSkin();
        display.runDeferredLayouts();
        display.sendPreExternalEventDispatchEvent();
        OS.gdk_threads_leave();
        OS.g_main_context_iteration(0, true);
        display.sendPostExternalEventDispatchEvent();
        display.runAsyncMessages(false);
      }
    } finally {
      display.tracker = oldTracker;
    }
    if (!isDisposed()) {
      if (!OS.GTK3) {
        update();
        drawRectangles(rectangles);
      }
    }
    ungrab();
    if (overlay != 0) {
      OS.gtk_widget_destroy(overlay);
      overlay = 0;
    }
    window = 0;
    return !cancelled;
  }
}
