class PlaceHold {
  void bringToTop(boolean force) {
    if (!gtk_widget_get_visible(shellHandle)) {
      return;
    }
    Display display = this.display;
    Shell activeShell = display.activeShell;
    if (activeShell == this) {
      return;
    }
    if (!force) {
      if (activeShell == null) {
        return;
      }
      if (!display.activePending) {
        long focusHandle = OS.gtk_window_get_focus(activeShell.shellHandle);
        if ((focusHandle != 0) && (!gtk_widget_has_focus(focusHandle))) {
          return;
        }
      }
    }
    boolean xFocus = false;
    if (activeShell != null) {
      if (OS.GTK_VERSION < OS.VERSION(2, 6, 8)) {
        xFocus = activeShell.isUndecorated();
      }
      display.activeShell = null;
      display.activePending = true;
    }
    long window = gtk_widget_get_window(shellHandle);
    if ((xFocus || ((style & SWT.ON_TOP) != 0)) && OS.GDK_WINDOWING_X11()) {
      long xDisplay;
      if (OS.GTK_VERSION >= OS.VERSION(2, 24, 0)) {
        xDisplay = OS.gdk_x11_display_get_xdisplay(OS.gdk_window_get_display(window));
      } else {
        xDisplay = OS.gdk_x11_drawable_get_xdisplay(window);
      }
      long xWindow;
      if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
        xWindow = OS.gdk_x11_window_get_xid(window);
      } else {
        xWindow = OS.gdk_x11_drawable_get_xid(window);
      }
      OS.gdk_error_trap_push();
      OS.XSetInputFocus(xDisplay, xWindow, RevertToParent, CurrentTime);
      OS.gdk_error_trap_pop();
    } else if (display.windowManager.toLowerCase().equals("metacity")) {
      OS.gdk_window_focus(window, display.lastUserEventTime);
    } else {
      OS.gdk_window_focus(window, GDK_CURRENT_TIME);
    }
    display.activeShell = this;
    display.activePending = true;
  }
}
