class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    Control control = null;
    boolean fixFocus = false;
    if (!enabled) {
      control = display.getFocusControl();
      fixFocus = isFocusAncestor(control);
    }
    enableWidget(enabled);
    if (fixFocus) {
      fixFocus(control);
    }
  }
}
