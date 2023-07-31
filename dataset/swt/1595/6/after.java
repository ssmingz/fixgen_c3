class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    if (enabled == getEnabled()) {
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
    enableWidget(enabled);
    if (fixFocus) {
      fixFocus(control);
    }
    if ((!enabled) || (isEnabled() && enabled)) {
      propagateChildren(enabled);
    }
  }
}
