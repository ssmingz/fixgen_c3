class PlaceHold {
  public void setValues(
      int selection, int minimum, int maximum, int thumb, int increment, int pageIncrement) {
    checkWidget();
    if (selection < 0) {
      return;
    }
    if (minimum < 0) {
      return;
    }
    if (maximum < 0) {
      return;
    }
    if (thumb < 1) {
      return;
    }
    if (((maximum - minimum) - thumb) < 0) {
      return;
    }
    if (increment < 1) {
      return;
    }
    if (pageIncrement < 1) {
      return;
    }
    this.increment = increment;
    this.pageIncrement = pageIncrement;
    SCROLLINFO info = new SCROLLINFO();
    info.cbSize = SCROLLINFO.sizeof;
    info.fMask = ((OS.SIF_POS | OS.SIF_PAGE) | OS.SIF_RANGE) | OS.SIF_DISABLENOSCROLL;
    info.nPos = selection;
    info.nMin = minimum;
    info.nMax = maximum;
    info.nPage = thumb;
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
