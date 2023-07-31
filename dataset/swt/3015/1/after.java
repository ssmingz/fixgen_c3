class PlaceHold {
  public void setMaximum(int value) {
    checkWidget();
    if (value < 0) {
      return;
    }
    SCROLLINFO info = new SCROLLINFO();
    info.cbSize = SCROLLINFO.sizeof;
    info.fMask = OS.SIF_RANGE | OS.SIF_DISABLENOSCROLL;
    OS.GetScrollInfo(handle, SB_CTL, info);
    if (((value - info.nMin) - info.nPage) < 1) {
      return;
    }
    info.nMax = value;
    OS.SetScrollInfo(handle, SB_CTL, info, (state & DISABLED) == 0);
    if ((state & DISABLED) != 0) {
      if (OS.IsWinCE) {
        OS.EnableWindow(handle, false);
      } else {
        OS.EnableScrollBar(handle, SB_CTL, ESB_DISABLE_BOTH);
      }
    }
    if (OS.GetFocus() == handle) {
      OS.PostMessage(handle, WM_SETFOCUS, 0, 0);
    }
  }
}
