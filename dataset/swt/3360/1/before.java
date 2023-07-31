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
    Control control = null;
    boolean fixFocus = false;
    if (!visible) {
      control = display.getFocusControl();
      fixFocus = isFocusAncestor(control);
    }
    topView().setHidden(!visible);
    if (!visible) {
      sendEvent(Hide);
      if (isDisposed()) {
        return;
      }
    }
    if (fixFocus) {
      fixFocus(control);
    }
  }
}
