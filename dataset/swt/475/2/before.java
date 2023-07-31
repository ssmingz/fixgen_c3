class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    if (((state & DISABLED) == 0) == enabled) {
      return;
    }
    Control control = null;
    boolean fixFocus = false;
    if (!enabled) {
      control = display.getFocusControl();
      fixFocus = isFocusAncestor(control);
    }
    if (enabled) {
      state &= ~DISABLED;
    } else {
      state |= DISABLED;
    }
    enableWidget(enabled);
    if (enabled) {
      if (enableWindow != 0) {
        OS.gdk_window_destroy(enableWindow);
        enableWindow = 0;
      }
    } else {
      int parentHandle = shellHandle;
      OS.gtk_widget_realize(parentHandle);
      int window = OS.GTK_WIDGET_WINDOW(parentHandle);
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
          OS.gdk_flush();
        }
        OS.gdk_window_set_user_data(enableWindow, parentHandle);
        OS.gdk_window_raise(enableWindow);
        OS.gdk_window_show(enableWindow);
      }
    }
    if (fixFocus) {
      fixFocus(control);
    }
    if (enabled && hasFocus) {
      if (!restoreFocus()) {
        traverseGroup(false);
      }
    }
  }
}
