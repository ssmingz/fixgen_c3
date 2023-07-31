class PlaceHold {
  void setScrollWidth(int scrollWidth) {
    this.scrollWidth = scrollWidth;
    if ((style & SWT.SIMPLE) != 0) {
      OS.SendMessage(handle, CB_SETHORIZONTALEXTENT, scrollWidth, 0);
      return;
    }
    boolean scroll = false;
    int count = ((int) (OS.SendMessage(handle, CB_GETCOUNT, 0, 0)));
    if (count > 3) {
      int maxWidth = 0;
      if (OS.IsWinCE || (OS.WIN32_VERSION < OS.VERSION(4, 10))) {
        RECT rect = new RECT();
        OS.SystemParametersInfo(SPI_GETWORKAREA, 0, rect, 0);
        maxWidth = (rect.right - rect.left) / 4;
      } else {
        int hmonitor = OS.MonitorFromWindow(handle, MONITOR_DEFAULTTONEAREST);
        MONITORINFO lpmi = new MONITORINFO();
        lpmi.cbSize = MONITORINFO.sizeof;
        OS.GetMonitorInfo(hmonitor, lpmi);
        maxWidth = (lpmi.rcWork_right - lpmi.rcWork_left) / 4;
      }
      scroll = scrollWidth > maxWidth;
    }
    boolean oldLockText = lockText;
    if ((style & SWT.READ_ONLY) == 0) {
      lockText = true;
    }
    if (scroll) {
      OS.SendMessage(handle, CB_SETDROPPEDWIDTH, 0, 0);
      OS.SendMessage(handle, CB_SETHORIZONTALEXTENT, scrollWidth, 0);
    } else {
      scrollWidth += OS.GetSystemMetrics(SM_CYHSCROLL);
      OS.SendMessage(handle, CB_SETDROPPEDWIDTH, scrollWidth, 0);
      OS.SendMessage(handle, CB_SETHORIZONTALEXTENT, 0, 0);
    }
    if ((style & SWT.READ_ONLY) == 0) {
      lockText = oldLockText;
    }
  }
}
