class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int height = 0;
    int width = 0;
    if ((wHint == SWT.DEFAULT) || (hHint == SWT.DEFAULT)) {
      int newFont;
      int oldFont = 0;
      int hDC = OS.GetDC(handle);
      newFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
      if (newFont != 0) {
        oldFont = OS.SelectObject(hDC, newFont);
      }
      TEXTMETRIC tm = (OS.IsUnicode) ? ((TEXTMETRIC) (new TEXTMETRICW())) : new TEXTMETRICA();
      OS.GetTextMetrics(hDC, tm);
      int count = ((style & SWT.SINGLE) != 0) ? 1 : OS.SendMessage(handle, EM_GETLINECOUNT, 0, 0);
      height = count * tm.tmHeight;
      RECT rect = new RECT();
      int flags = (OS.DT_CALCRECT | OS.DT_EDITCONTROL) | OS.DT_NOPREFIX;
      boolean wrap = ((style & SWT.MULTI) != 0) && ((style & SWT.WRAP) != 0);
      if (wrap && (wHint != SWT.DEFAULT)) {
        flags |= OS.DT_WORDBREAK;
        rect.right = wHint;
      }
      int length = OS.GetWindowTextLength(handle);
      if (length != 0) {
        TCHAR buffer = new TCHAR(getCodePage(), length + 1);
        OS.GetWindowText(handle, buffer, length + 1);
        OS.DrawText(hDC, buffer, length, rect, flags);
        width = rect.right - rect.left;
      }
      if (wrap && (hHint == SWT.DEFAULT)) {
        int newHeight = rect.bottom - rect.top;
        if (newHeight != 0) {
          height = newHeight;
        }
      }
      if (newFont != 0) {
        OS.SelectObject(hDC, oldFont);
      }
      OS.ReleaseDC(handle, hDC);
    }
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
    Rectangle trim = computeTrim(0, 0, width, height);
    return new Point(trim.width, trim.height);
  }
}
