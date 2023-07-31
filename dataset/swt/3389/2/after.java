class PlaceHold {
  void _setMaximized(boolean maximized) {
    swFlags = (maximized) ? OS.SW_SHOWMAXIMIZED : OS.SW_RESTORE;
    if (OS.IsWinCE) {
      if (maximized) {
        RECT rect = new RECT();
        OS.SystemParametersInfo(SPI_GETWORKAREA, 0, rect, 0);
        int width = rect.right - rect.left;
        int height = rect.bottom - rect.top;
        if (OS.IsPPC) {
          if (menuBar != null) {
            long hwndCB = menuBar.hwndCB;
            RECT rectCB = new RECT();
            OS.GetWindowRect(hwndCB, rectCB);
            height -= rectCB.bottom - rectCB.top;
          }
        }
        int flags = (OS.SWP_NOZORDER | OS.SWP_DRAWFRAME) | OS.SWP_NOACTIVATE;
        SetWindowPos(handle, 0, rect.left, rect.top, width, height, flags);
      }
    } else {
      if (!OS.IsWindowVisible(handle)) {
        return;
      }
      if (maximized == OS.IsZoomed(handle)) {
        return;
      }
      OS.ShowWindow(handle, swFlags);
      OS.UpdateWindow(handle);
    }
  }
}
