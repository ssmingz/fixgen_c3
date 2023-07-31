class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if (((state & HIDDEN) == 0) == visible) {
      return;
    }
    int topHandle = topHandle();
    if (visible) {
      sendEvent(Show);
      if (isDisposed()) {
        return;
      }
      state &= ~HIDDEN;
      if ((state & (ZERO_WIDTH | ZERO_HEIGHT)) == 0) {
        if (enableWindow != 0) {
          OS.gdk_window_show_unraised(enableWindow);
        }
        OS.gtk_widget_show(topHandle);
      }
    } else {
      Control control = null;
      boolean fixFocus = false;
      if (display.focusEvent != SWT.FocusOut) {
        control = display.getFocusControl();
        fixFocus = isFocusAncestor(control);
      }
      state |= HIDDEN;
      if (fixFocus) {
        gtk_widget_set_visible(topHandle, false);
        fixFocus(control);
        if (isDisposed()) {
          return;
        }
        gtk_widget_set_visible(topHandle, true);
      }
      OS.gtk_widget_hide(topHandle);
      if (isDisposed()) {
        return;
      }
      if (enableWindow != 0) {
        OS.gdk_window_hide(enableWindow);
      }
      sendEvent(Hide);
    }
  }
}
