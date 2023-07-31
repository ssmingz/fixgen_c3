class PlaceHold {
  public void setCursor(Cursor cursor) {
    checkWidget();
    hCursor = 0;
    if (cursor != null) {
      if (cursor.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      hCursor = cursor.handle;
    }
    if (OS.IsWinCE) {
      OS.SetCursor(hCursor);
      return;
    }
    int hwndCursor = OS.GetCapture();
    if (hwndCursor == 0) {
      POINT pt = new POINT();
      if (!OS.GetCursorPos(pt)) {
        return;
      }
      int hwnd = hwndCursor = OS.WindowFromPoint(pt);
      while ((hwnd != 0) && (hwnd != handle)) {
        hwnd = OS.GetParent(hwnd);
      }
      if (hwnd == 0) {
        return;
      }
    }
    int lParam = OS.HTCLIENT | (OS.WM_MOUSEMOVE << 16);
    OS.SendMessage(hwndCursor, WM_SETCURSOR, hwndCursor, lParam);
  }
}
