class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    if (((state & DISABLED) == 0) == enabled) {
      return;
    }
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
      OS.gtk_widget_realize(handle);
      int parentHandle = parent.eventHandle();
      int window = parent.eventWindow();
      int topHandle = topHandle();
      GdkWindowAttr attributes = new GdkWindowAttr();
      GtkAllocation allocation = new GtkAllocation();
      if (OS.GTK_VERSION >= OS.VERSION(2, 18, 0)) {
        OS.gtk_widget_get_allocation(topHandle, allocation);
        attributes.x = allocation.x;
        attributes.y = allocation.y;
        attributes.width = ((state & ZERO_WIDTH) != 0) ? 0 : allocation.width;
        attributes.height = ((state & ZERO_HEIGHT) != 0) ? 0 : allocation.height;
      } else {
        attributes.x = OS.GTK_WIDGET_X(topHandle);
        attributes.y = OS.GTK_WIDGET_Y(topHandle);
        attributes.width = ((state & ZERO_WIDTH) != 0) ? 0 : OS.GTK_WIDGET_WIDTH(topHandle);
        attributes.height = ((state & ZERO_HEIGHT) != 0) ? 0 : OS.GTK_WIDGET_HEIGHT(topHandle);
      }
      attributes.event_mask = 0xffffffff & (~OS.ExposureMask);
      attributes.wclass = OS.GDK_INPUT_ONLY;
      attributes.window_type = OS.GDK_WINDOW_CHILD;
      enableWindow = OS.gdk_window_new(window, attributes, OS.GDK_WA_X | OS.GDK_WA_Y);
      if (enableWindow != 0) {
        OS.gdk_window_set_user_data(enableWindow, parentHandle);
        if (!OS.GDK_WINDOWING_X11()) {
          OS.gdk_window_raise(enableWindow);
        } else {
          restackWindow(enableWindow, gtk_widget_get_window(topHandle), true);
        }
        if (gtk_widget_get_visible(topHandle)) {
          OS.gdk_window_show_unraised(enableWindow);
        }
      }
    }
    if (fixFocus) {
      fixFocus(control);
    }
  }
}
