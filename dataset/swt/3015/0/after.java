class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    if (value < 0) {
      return;
    }
    SCROLLINFO info = new SCROLLINFO();
    info.cbSize = SCROLLINFO.sizeof;
    int hwnd = hwndScrollBar();
    int type = scrollBarType();
    info.fMask = OS.SIF_RANGE | OS.SIF_DISABLENOSCROLL;
    OS.GetScrollInfo(hwnd, type, info);
    if (((info.nMax - value) - info.nPage) < 1) {
      return;
    }
    info.nMin = value;
    OS.SetScrollInfo(hwnd, type, info, true);
    if ((state & HIDDEN) != 0) {
      if (!OS.IsWinCE) {
        OS.ShowScrollBar(hwnd, type, false);
      }
    }
    if ((state & DISABLED) != 0) {
      if (!OS.IsWinCE) {
        OS.EnableScrollBar(hwnd, type, ESB_DISABLE_BOTH);
      }
    }
  }
}
