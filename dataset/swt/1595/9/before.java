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
    if (fixFocus) {
      fixFocus(control);
    }
  }
}
