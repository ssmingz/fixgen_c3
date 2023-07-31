class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int count = OS.SendMessage(handle, CB_GETCOUNT, 0, 0);
    int itemHeight = OS.SendMessage(handle, CB_GETITEMHEIGHT, 0, 0);
    int width = 0;
    int height = 0;
    if ((style & SWT.SIMPLE) != 0) {
      height = count * itemHeight;
    }
    int newFont;
    int oldFont = 0;
    int hDC = OS.GetDC(handle);
    newFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
    if (newFont != 0) {
      oldFont = OS.SelectObject(hDC, newFont);
    }
    RECT rect = new RECT();
    int flags = OS.DT_CALCRECT | OS.DT_NOPREFIX;
    int length = OS.GetWindowTextLength(handle);
    int cp = getCodePage();
    TCHAR buffer = new TCHAR(cp, length + 1);
    OS.GetWindowText(handle, buffer, length + 1);
    OS.DrawText(hDC, buffer, length, rect, flags);
    width = Math.max(width, rect.right - rect.left);
    for (int i = 0; i < count; i++) {
      length = OS.SendMessage(handle, CB_GETLBTEXTLEN, i, 0);
      if (length != OS.CB_ERR) {
        if ((length + 1) > buffer.length()) {
          buffer = new TCHAR(cp, length + 1);
        }
        int result = OS.SendMessage(handle, CB_GETLBTEXT, i, buffer);
        if (result != OS.CB_ERR) {
          OS.DrawText(hDC, buffer, length, rect, flags);
          width = Math.max(width, rect.right - rect.left);
        }
      }
    }
    TEXTMETRIC tm = new TEXTMETRIC();
    OS.GetTextMetrics(hDC, tm);
    if (newFont != 0) {
      OS.SelectObject(hDC, oldFont);
    }
    OS.ReleaseDC(handle, hDC);
    if (width == 0) {
      width = DEFAULT_WIDTH;
    }
    if (height == 0) {
      height = DEFAULT_HEIGHT;
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    int border = OS.GetSystemMetrics(SM_CXEDGE);
    width += OS.GetSystemMetrics(SM_CXVSCROLL) + ((tm.tmInternalLeading + border) * 2);
    int textHeight = OS.SendMessage(handle, CB_GETITEMHEIGHT, -1, 0);
    if ((style & SWT.DROP_DOWN) != 0) {
      height = textHeight + 6;
    } else {
      height += textHeight + 10;
    }
    return new Point(width, height);
  }
}
