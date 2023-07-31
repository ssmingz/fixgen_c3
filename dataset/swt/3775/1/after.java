class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    int border = getBorderWidth();
    if ((style & SWT.SEPARATOR) != 0) {
      int lineWidth = OS.GetSystemMetrics(SM_CXBORDER);
      if ((style & SWT.HORIZONTAL) != 0) {
        width = DEFAULT_WIDTH;
        height = lineWidth * 2;
      } else {
        width = lineWidth * 2;
        height = DEFAULT_HEIGHT;
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
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    boolean isBitmap = (bits & OS.SS_BITMAP) == OS.SS_BITMAP;
    boolean isIcon = (bits & OS.SS_ICON) == OS.SS_ICON;
    if (isBitmap || isIcon) {
      if (image != null) {
        Rectangle rect = image.getBounds();
        width = rect.width;
        height = rect.height;
      }
    } else {
      int hDC = OS.GetDC(handle);
      int newFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
      int oldFont = OS.SelectObject(hDC, newFont);
      RECT rect = new RECT();
      int flags = (OS.DT_CALCRECT | OS.DT_EDITCONTROL) | OS.DT_EXPANDTABS;
      if (((style & SWT.WRAP) != 0) && (wHint != SWT.DEFAULT)) {
        flags |= OS.DT_WORDBREAK;
        rect.right = wHint;
      }
      int length = OS.GetWindowTextLength(handle);
      TCHAR buffer = new TCHAR(getCodePage(), length + 1);
      OS.GetWindowText(handle, buffer, length + 1);
      OS.DrawText(hDC, buffer, length, rect, flags);
      width = rect.right - rect.left;
      height = rect.bottom - rect.top;
      if (height == 0) {
        TEXTMETRIC tm = (OS.IsUnicode) ? ((TEXTMETRIC) (new TEXTMETRICW())) : new TEXTMETRICA();
        OS.GetTextMetrics(hDC, tm);
        height = tm.tmHeight;
      }
      if (newFont != 0) {
        OS.SelectObject(hDC, oldFont);
      }
      OS.ReleaseDC(handle, hDC);
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    width += border * 2;
    height += border * 2;
    if (OS.IsWinCE) {
      if ((!isBitmap) && (!isIcon)) {
        width += 2;
      }
    }
    return new Point(width, height);
  }
}
