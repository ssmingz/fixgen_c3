class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int border = getBorderWidth();
    int width = 0;
    int height = 0;
    if ((style & SWT.ARROW) != 0) {
      if ((style & (SWT.UP | SWT.DOWN)) != 0) {
        width += OS.GetSystemMetrics(SM_CXVSCROLL);
        height += OS.GetSystemMetrics(SM_CYVSCROLL);
      } else {
        width += OS.GetSystemMetrics(SM_CXHSCROLL);
        height += OS.GetSystemMetrics(SM_CYHSCROLL);
      }
      if (wHint != SWT.DEFAULT) {
        width = wHint;
      }
      if (hHint != SWT.DEFAULT) {
        height = hHint;
      }
      width += border * 2;
      height += border * 2;
      return new Point(width, height);
    }
    int extra = 0;
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    if ((bits & (OS.BS_BITMAP | OS.BS_ICON)) == 0) {
      int oldFont = 0;
      int hDC = OS.GetDC(handle);
      int newFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
      if (newFont != 0) {
        oldFont = OS.SelectObject(hDC, newFont);
      }
      TEXTMETRIC lptm = (OS.IsUnicode) ? ((TEXTMETRIC) (new TEXTMETRICW())) : new TEXTMETRICA();
      OS.GetTextMetrics(hDC, lptm);
      int length = OS.GetWindowTextLength(handle);
      if (length == 0) {
        height += lptm.tmHeight;
      } else {
        extra = Math.max(8, lptm.tmAveCharWidth);
        TCHAR buffer = new TCHAR(getCodePage(), length + 1);
        OS.GetWindowText(handle, buffer, length + 1);
        RECT rect = new RECT();
        int flags = OS.DT_CALCRECT | OS.DT_SINGLELINE;
        OS.DrawText(hDC, buffer, length, rect, flags);
        width += rect.right - rect.left;
        height += rect.bottom - rect.top;
      }
      if (newFont != 0) {
        OS.SelectObject(hDC, oldFont);
      }
      OS.ReleaseDC(handle, hDC);
    } else if (image != null) {
      Rectangle rect = image.getBounds();
      width = rect.width;
      height = rect.height;
      extra = 8;
    }
    if ((style & (SWT.CHECK | SWT.RADIO)) != 0) {
      width += CheckWidth + extra;
      height = Math.max(height, CheckHeight + 3);
    }
    if ((style & (SWT.PUSH | SWT.TOGGLE)) != 0) {
      width += 12;
      height += 10;
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    width += border * 2;
    height += border * 2;
    return new Point(width, height);
  }
}
