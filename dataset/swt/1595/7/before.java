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
        OS.gdk_window_set_user_data(enableWindow, 0);
        OS.gdk_window_destroy(enableWindow);
        enableWindow = 0;
      }
    } else {
      OS.gtk_widget_realize(handle);
      int parentHandle = parent.parentingHandle();
      int window = OS.GTK_WIDGET_WINDOW(parentHandle);
      Rectangle rect = getBounds();
      GdkWindowAttr attributes = new GdkWindowAttr();
      attributes.x = rect.x;
      attributes.y = rect.y;
      attributes.width = rect.width;
      attributes.height = rect.height;
      attributes.event_mask = 0xffffffff & (~OS.ExposureMask);
      attributes.wclass = OS.GDK_INPUT_ONLY;
      attributes.window_type = OS.GDK_WINDOW_CHILD;
      enableWindow = OS.gdk_window_new(window, attributes, OS.GDK_WA_X | OS.GDK_WA_Y);
      if (enableWindow != 0) {
        int topHandle = topHandle();
        OS.gdk_window_set_user_data(enableWindow, parentHandle);
        if (!OS.GDK_WINDOWING_X11()) {
          OS.gdk_window_raise(enableWindow);
        } else {
          int topWindow = OS.GTK_WIDGET_WINDOW(topHandle);
          int xDisplay = OS.gdk_x11_drawable_get_xdisplay(topWindow);
          int xWindow = OS.gdk_x11_drawable_get_xid(enableWindow);
          int xScreen = OS.XDefaultScreen(xDisplay);
          int flags = OS.CWStackMode | OS.CWSibling;
          XWindowChanges changes = new XWindowChanges();
          changes.sibling = OS.gdk_x11_drawable_get_xid(topWindow);
          changes.stack_mode = OS.Above;
          OS.XReconfigureWMWindow(xDisplay, xWindow, xScreen, flags, changes);
        }
        if (OS.GTK_WIDGET_VISIBLE(topHandle)) {
          OS.gdk_window_show(enableWindow);
        }
      }
    }
    if (fixFocus) {
      fixFocus(control);
    }
  }
}
