class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    int hwnd = hwndScrollBar();
    int type = scrollBarType();
    int flags = OS.ESB_DISABLE_BOTH;
    if (enabled) {
      flags = OS.ESB_ENABLE_BOTH;
    }
    if (OS.IsWinCE) {
    } else {
      OS.EnableScrollBar(hwnd, type, flags);
    }
    state &= ~DISABLED;
    if (!enabled) {
      state |= DISABLED;
    }
  }
}
