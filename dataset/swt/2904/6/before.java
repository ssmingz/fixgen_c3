class PlaceHold {
  void setItemHeight(boolean fixScroll) {
    int topIndex = getTopIndex();
    if (fixScroll && (topIndex != 0)) {
      setRedraw(false);
      setTopIndex(0);
    }
    if (itemHeight == (-1)) {
      int hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
      OS.SendMessage(handle, WM_SETFONT, hFont, 0);
    } else {
      forceResize();
      RECT rect = new RECT();
      OS.GetWindowRect(handle, rect);
      int width = rect.right - rect.left;
      int height = rect.bottom - rect.top;
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      OS.SetWindowLong(handle, GWL_STYLE, bits | OS.LVS_OWNERDRAWFIXED);
      int flags = ((OS.SWP_NOACTIVATE | OS.SWP_NOMOVE) | OS.SWP_NOREDRAW) | OS.SWP_NOZORDER;
      ignoreResize = true;
      SetWindowPos(handle, 0, 0, 0, width, height + 1, flags);
      SetWindowPos(handle, 0, 0, 0, width, height, flags);
      ignoreResize = false;
      OS.SetWindowLong(handle, GWL_STYLE, bits);
    }
    if (fixScroll && (topIndex != 0)) {
      setTopIndex(topIndex);
      setRedraw(true);
    }
  }
}
