class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    int flags = OS.ESB_DISABLE_BOTH;
    if (enabled) {
      flags = OS.ESB_ENABLE_BOTH;
    }
    if (OS.IsWinCE) {
      error(ERROR_NOT_IMPLEMENTED);
    }
    OS.EnableScrollBar(handle, SB_CTL, flags);
    state &= ~DISABLED;
    if (!enabled) {
      state |= DISABLED;
    }
  }
}
