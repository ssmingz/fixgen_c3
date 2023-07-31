class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if (drawCount != 0) {
      if (((state & HIDDEN) == 0) == visible) {
        return;
      }
    } else {
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      if (((bits & OS.WS_VISIBLE) != 0) == visible) {
        return;
      }
    }
    if (visible) {
      sendEvent(Show);
      if (isDisposed()) {
        return;
      }
    }
    boolean fixFocus = false;
    if (!visible) {
      fixFocus = isFocusAncestor();
    }
    if (drawCount != 0) {
      state = (visible) ? state & (~HIDDEN) : state | HIDDEN;
    } else {
      OS.ShowWindow(handle, visible ? OS.SW_SHOW : OS.SW_HIDE);
      if (isDisposed()) {
        return;
      }
    }
    if (!visible) {
      sendEvent(Hide);
      if (isDisposed()) {
        return;
      }
    }
    if (fixFocus) {
      fixFocus();
    }
  }
}
