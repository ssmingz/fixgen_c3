class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    state &= ~DISABLED;
    if (!enabled) {
      state |= DISABLED;
    }
    if (!Display.TrimEnabled) {
      super.setEnabled(enabled);
    } else if (isActive()) {
      setItemEnabled(SC_CLOSE, enabled);
    }
  }
}
