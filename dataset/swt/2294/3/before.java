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
      data.device = getDisplay();
      data.foreground = getForegroundPixel();
      data.background = getBackgroundPixel();
      data.hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
      data.hwnd = handle;
    }
    return hDC;
  }
}
