class PlaceHold {
  public int internal_new_GC(GCData data) {
    checkWidget();
    int hDC;
    if ((data == null) || (data.ps == null)) {
      hDC = OS.GetDC(handle);
    } else {
      hDC = OS.BeginPaint(handle, data.ps);
    }
    if (hDC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      if (((OS.WIN32_MAJOR << 16) | OS.WIN32_MINOR) >= ((4 << 16) | 10)) {
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
      data.foreground = getForegroundPixel();
      data.background = getBackgroundPixel();
      data.hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
      data.hwnd = handle;
    }
    return hDC;
  }
}
