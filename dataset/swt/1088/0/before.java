class PlaceHold {
  @Override
  public void setEnabled(boolean enabled) {
    checkWidget();
    if (((state & DISABLED) == 0) == enabled) {
      return;
    }
    Display display = this.display;
    Control control = null;
    boolean fixFocus = false;
    if (!enabled) {
      if (display.focusEvent != SWT.FocusOut) {
        control = display.getFocusControl();
        fixFocus = isFocusAncestor(control);
      }
    }
    if (enabled) {
      state &= ~DISABLED;
    } else {
      state |= DISABLED;
    }
    enableWidget(enabled);
    if (isDisposed()) {
      return;
    }
    if (enabled) {
      if (enableWindow != 0) {
        OS.gdk_window_set_user_data(enableWindow, 0);
        OS.gdk_window_destroy(enableWindow);
        enableWindow = 0;
      }
    } else {
      long parentHandle = shellHandle;
      OS.gtk_widget_realize(parentHandle);
      long window = gtk_widget_get_window(parentHandle);
      Rectangle rect = getBounds();
      GdkWindowAttr attributes = new GdkWindowAttr();
      attributes.width = rect.width;
      attributes.height = rect.height;
      attributes.event_mask = 0xffffffff & (~OS.ExposureMask);
      attributes.wclass = OS.GDK_INPUT_ONLY;
      attributes.window_type = OS.GDK_WINDOW_CHILD;
      enableWindow = OS.gdk_window_new(window, attributes, 0);
      if (enableWindow != 0) {
        if (cursor != null) {
          OS.gdk_window_set_cursor(enableWindow, handle);
          if (!OS.GDK_WINDOWING_X11()) {
            OS.gdk_flush();
          } else {
            long xDisplay = OS.gdk_x11_display_get_xdisplay(OS.gdk_display_get_default());
            OS.XFlush(xDisplay);
          }
        }
        OS.gdk_window_set_user_data(enableWindow, parentHandle);
        OS.gdk_window_show(enableWindow);
      }
    }
    if (fixFocus) {
      fixFocus(control);
    }
    if (enabled && (display.activeShell == this)) {
      if (!restoreFocus()) {
        traverseGroup(false);
      }
    }
  }
}
