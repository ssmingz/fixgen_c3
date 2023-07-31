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
        int focusHandle = OS.gtk_window_get_focus(activeShell.shellHandle);
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
    int window = OS.GTK_WIDGET_WINDOW(shellHandle);
    if ((xFocus || ((style & SWT.ON_TOP) != 0)) && OS.GDK_WINDOWING_X11()) {
      int xDisplay = OS.gdk_x11_drawable_get_xdisplay(window);
      int xWindow = OS.gdk_x11_drawable_get_xid(window);
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
