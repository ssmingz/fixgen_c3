class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if (visible) {
      if ((state & HIDDEN) == 0) {
        return;
      }
      state &= ~HIDDEN;
    } else {
      if ((state & HIDDEN) != 0) {
        return;
      }
      state |= HIDDEN;
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
    OS.HIViewSetVisible(topHandle(), visible);
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
