class PlaceHold {
  public int internal_new_GC(GCData data) {
    checkWidget();
    int hwnd = handle;
    if ((data != null) && (data.hwnd != 0)) {
      hwnd = data.hwnd;
    }
    if (data != null) {
      data.hwnd = hwnd;
    }
    int hDC = 0;
    if ((data == null) || (data.ps == null)) {
      hDC = OS.GetDC(hwnd);
    } else {
      hDC = OS.BeginPaint(hwnd, data.ps);
    }
    if (hDC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(4, 10))) {
        int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
        if ((data.style & mask) != 0) {
          data.layout = ((data.style & SWT.RIGHT_TO_LEFT) != 0) ? OS.LAYOUT_RTL : 0;
        } else {
          int flags = OS.GetLayout(hDC);
          if ((flags & OS.LAYOUT_RTL) != 0) {
            data.style |= SWT.RIGHT_TO_LEFT | SWT.MIRRORED;
          } else {
            data.style |= SWT.LEFT_TO_RIGHT;
          }
        }
      } else {
        data.style |= SWT.LEFT_TO_RIGHT;
      }
      data.device = display;
      int foreground = getForegroundPixel();
      if (foreground != OS.GetTextColor(hDC)) {
        data.foreground = foreground;
      }
      Control control = findBackgroundControl();
      if (control == null) {
        control = this;
      }
      int background = control.getBackgroundPixel();
      if (background != OS.GetBkColor(hDC)) {
        data.background = background;
      }
      data.hFont = OS.SendMessage(hwnd, WM_GETFONT, 0, 0);
      data.uiState = ((int) (OS.SendMessage(hwnd, WM_QUERYUISTATE, 0, 0)));
    }
    return hDC;
  }
}
