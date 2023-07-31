class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    if (value < 0) {
      return;
    }
    SCROLLINFO info = new SCROLLINFO();
    info.cbSize = SCROLLINFO.sizeof;
    info.fMask = OS.SIF_RANGE | OS.SIF_DISABLENOSCROLL;
    OS.GetScrollInfo(handle, SB_CTL, info);
    if (((info.nMax - value) - info.nPage) < 1) {
      return;
    }
    info.nMin = value;
    OS.SetScrollInfo(handle, SB_CTL, info, true);
    if ((state & DISABLED) != 0) {
      OS.EnableScrollBar(handle, SB_CTL, ESB_DISABLE_BOTH);
    }
    if (OS.GetFocus() == handle) {
      OS.PostMessage(handle, WM_SETFOCUS, 0, 0);
    }
  }
}
