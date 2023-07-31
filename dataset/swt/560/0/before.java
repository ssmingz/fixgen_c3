class PlaceHold {
  public void setCursor(Cursor cursor) {
    checkWidget();
    hCursor = 0;
    if (cursor != null) {
      hCursor = cursor.handle;
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
