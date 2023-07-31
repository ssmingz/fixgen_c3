class PlaceHold {
  public void remove(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    int count = ((int) (OS.SendMessage(handle, CB_GETCOUNT, 0, 0)));
    if (!(((0 <= start) && (start <= end)) && (end < count))) {
      error(ERROR_INVALID_RANGE);
    }
    int textLength = OS.GetWindowTextLength(handle);
    RECT rect = null;
    long hDC = 0;
    long oldFont = 0;
    long newFont = 0;
    int newWidth = 0;
    if ((style & SWT.H_SCROLL) != 0) {
      rect = new RECT();
      hDC = OS.GetDC(handle);
      newFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
      if (newFont != 0) {
        oldFont = OS.SelectObject(hDC, newFont);
      }
    }
    int cp = getCodePage();
    int flags = (OS.DT_CALCRECT | OS.DT_SINGLELINE) | OS.DT_NOPREFIX;
    for (int i = start; i <= end; i++) {
      TCHAR buffer = null;
      if ((style & SWT.H_SCROLL) != 0) {
        int length = ((int) (OS.SendMessage(handle, CB_GETLBTEXTLEN, start, 0)));
        if (length == OS.CB_ERR) {
          break;
        }
        buffer = new TCHAR(cp, length + 1);
        int result = ((int) (OS.SendMessage(handle, CB_GETLBTEXT, start, buffer)));
        if (result == OS.CB_ERR) {
          break;
        }
      }
      int result = ((int) (OS.SendMessage(handle, CB_DELETESTRING, start, 0)));
      if (result == OS.CB_ERR) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
      if ((style & SWT.H_SCROLL) != 0) {
        OS.DrawText(hDC, buffer, -1, rect, flags);
        newWidth = Math.max(newWidth, rect.right - rect.left);
      }
    }
    if ((style & SWT.H_SCROLL) != 0) {
      if (newFont != 0) {
        OS.SelectObject(hDC, oldFont);
      }
      OS.ReleaseDC(handle, hDC);
      setScrollWidth(newWidth, false);
    }
    if (textLength != OS.GetWindowTextLength(handle)) {
      sendEvent(Modify);
      if (isDisposed()) {
        return;
      }
    }
    if ((style & SWT.READ_ONLY) != 0) {
      count = ((int) (OS.SendMessage(handle, CB_GETCOUNT, 0, 0)));
      if (count == 0) {
        OS.InvalidateRect(handle, null, true);
      }
    }
  }
}
