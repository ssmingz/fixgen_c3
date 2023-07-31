class PlaceHold {
  public Rectangle getClientArea() {
    checkWidget();
    forceResize();
    RECT rect = new RECT();
    OS.GetClientRect(handle, rect);
    int newFont;
    int oldFont = 0;
    int hDC = OS.GetDC(handle);
    newFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
    if (newFont != 0) {
      oldFont = OS.SelectObject(hDC, newFont);
    }
    TEXTMETRIC tm = new TEXTMETRIC();
    OS.GetTextMetrics(hDC, tm);
    if (newFont != 0) {
      OS.SelectObject(hDC, oldFont);
    }
    OS.ReleaseDC(handle, hDC);
    int inset = 3;
    int x = inset;
    int y = tm.tmHeight;
    return new Rectangle(x, y, rect.right - (inset * 2), (rect.bottom - y) - inset);
  }
}
