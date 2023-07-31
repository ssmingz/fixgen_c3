class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    int topHandle = topHandle();
    if (OS.GTK_WIDGET_VISIBLE(topHandle) == visible) {
      return;
    }
    if (visible) {
      sendEvent(Show);
      if (isDisposed()) {
        return;
      }
      if (enableWindow != 0) {
        OS.gdk_window_show(enableWindow);
      }
      OS.gtk_widget_show(topHandle);
    } else {
      Control control = null;
      boolean fixFocus = false;
      if (!visible) {
        control = display.getFocusControl();
        fixFocus = isFocusAncestor(control);
      }
      if (fixFocus) {
        int flags = OS.GTK_WIDGET_FLAGS(topHandle);
        OS.GTK_WIDGET_UNSET_FLAGS(topHandle, GTK_VISIBLE);
        fixFocus(control);
        if (isDisposed()) {
          return;
        }
        if ((flags & OS.GTK_VISIBLE) != 0) {
          OS.GTK_WIDGET_SET_FLAGS(topHandle, GTK_VISIBLE);
        }
      }
      OS.gtk_widget_hide(topHandle);
      if (enableWindow != 0) {
        OS.gdk_window_hide(enableWindow);
      }
      sendEvent(Hide);
    }
  }
}
