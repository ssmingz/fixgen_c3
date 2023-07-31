class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    if ((style & SWT.VERTICAL) != 0) {
      RECT rect = new RECT();
      TBBUTTON lpButton = new TBBUTTON();
      int count = ((int) (OS.SendMessage(handle, TB_BUTTONCOUNT, 0, 0)));
      for (int i = 0; i < count; i++) {
        OS.SendMessage(handle, TB_GETITEMRECT, i, rect);
        height = Math.max(height, rect.bottom);
        OS.SendMessage(handle, TB_GETBUTTON, i, lpButton);
        if ((lpButton.fsStyle & OS.BTNS_SEP) != 0) {
          TBBUTTONINFO info = new TBBUTTONINFO();
          info.cbSize = TBBUTTONINFO.sizeof;
          info.dwMask = OS.TBIF_SIZE;
          OS.SendMessage(handle, TB_GETBUTTONINFO, lpButton.idCommand, info);
          width = Math.max(width, info.cx);
        } else {
          width = Math.max(width, rect.right);
        }
      }
    } else {
      RECT oldRect = new RECT();
      OS.GetWindowRect(handle, oldRect);
      int oldWidth = oldRect.right - oldRect.left;
      int oldHeight = oldRect.bottom - oldRect.top;
      int border = getBorderWidth();
      int newWidth = (wHint == SWT.DEFAULT) ? 0x3fff : wHint + (border * 2);
      int newHeight = (hHint == SWT.DEFAULT) ? 0x3fff : hHint + (border * 2);
      boolean redraw = getDrawing() && OS.IsWindowVisible(handle);
      ignoreResize = true;
      if (redraw) {
        OS.UpdateWindow(handle);
      }
      int flags = ((OS.SWP_NOACTIVATE | OS.SWP_NOMOVE) | OS.SWP_NOREDRAW) | OS.SWP_NOZORDER;
      SetWindowPos(handle, 0, 0, 0, newWidth, newHeight, flags);
      int count = ((int) (OS.SendMessage(handle, TB_BUTTONCOUNT, 0, 0)));
      if (count != 0) {
        RECT rect = new RECT();
        OS.SendMessage(handle, TB_GETITEMRECT, count - 1, rect);
        width = Math.max(width, rect.right);
        height = Math.max(height, rect.bottom);
      }
      SetWindowPos(handle, 0, 0, 0, oldWidth, oldHeight, flags);
      if (redraw) {
        OS.ValidateRect(handle, null);
      }
      ignoreResize = false;
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
    width = trim.width;
    height = trim.height;
    return new Point(width, height);
  }
}
