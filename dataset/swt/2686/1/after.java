class PlaceHold {
  public void setThumb(int value) {
    checkWidget();
    if (value < 1) {
      return;
    }
    SCROLLINFO info = new SCROLLINFO();
    info.cbSize = SCROLLINFO.sizeof;
    info.fMask = (OS.SIF_PAGE | OS.SIF_RANGE) | OS.SIF_DISABLENOSCROLL;
    OS.GetScrollInfo(handle, SB_CTL, info);
    if (((info.nMax - info.nMin) - value) < 0) {
      return;
    }
    info.nPage = value;
    if (info.nPage != 0) {
      info.nPage++;
    }
    OS.SetScrollInfo(handle, SB_CTL, info, true);
    if ((state & DISABLED) != 0) {
      if (OS.IsWinCE) {
        error(ERROR_NOT_IMPLEMENTED);
      }
      OS.EnableScrollBar(handle, SB_CTL, ESB_DISABLE_BOTH);
    }
    if (OS.GetFocus() == handle) {
      OS.PostMessage(handle, WM_SETFOCUS, 0, 0);
    }
  }
}
