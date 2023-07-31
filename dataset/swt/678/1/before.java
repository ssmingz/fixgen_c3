class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    int border = getBorderWidth();
    int newWidth = (wHint == SWT.DEFAULT) ? 0x3fff : wHint + (border * 2);
    int newHeight = (hHint == SWT.DEFAULT) ? 0x3fff : hHint + (border * 2);
    int count = ((int) (OS.SendMessage(handle, RB_GETBANDCOUNT, 0, 0)));
    if (count != 0) {
      ignoreResize = true;
      boolean redraw = false;
      if (OS.IsWindowVisible(handle)) {
        if (OS.COMCTL32_MAJOR >= 6) {
          redraw = true;
          OS.UpdateWindow(handle);
          OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
        } else {
          redraw = drawCount == 0;
          if (redraw) {
            OS.UpdateWindow(handle);
            OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
          }
        }
      }
      RECT oldRect = new RECT();
      OS.GetWindowRect(handle, oldRect);
      int oldWidth = oldRect.right - oldRect.left;
      int oldHeight = oldRect.bottom - oldRect.top;
      int flags = ((OS.SWP_NOACTIVATE | OS.SWP_NOMOVE) | OS.SWP_NOREDRAW) | OS.SWP_NOZORDER;
      SetWindowPos(handle, 0, 0, 0, newWidth, newHeight, flags);
      RECT rect = new RECT();
      OS.SendMessage(handle, RB_GETRECT, count - 1, rect);
      height = Math.max(height, rect.bottom);
      SetWindowPos(handle, 0, 0, 0, oldWidth, oldHeight, flags);
      REBARBANDINFO rbBand = new REBARBANDINFO();
      rbBand.cbSize = REBARBANDINFO.sizeof;
      rbBand.fMask = OS.RBBIM_IDEALSIZE | OS.RBBIM_STYLE;
      int rowWidth = 0;
      for (int i = 0; i < count; i++) {
        OS.SendMessage(handle, RB_GETBANDINFO, i, rbBand);
        if ((rbBand.fStyle & OS.RBBS_BREAK) != 0) {
          width = Math.max(width, rowWidth);
          rowWidth = 0;
        }
        rowWidth += rbBand.cxIdeal + getMargin(i);
      }
      width = Math.max(width, rowWidth);
      if (redraw) {
        if (OS.COMCTL32_MAJOR >= 6) {
          OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
        } else {
          OS.SendMessage(handle, WM_SETREDRAW, 1, 0);
        }
      }
      ignoreResize = false;
    }
    if (width == 0) {
      width = DEFAULT_COOLBAR_WIDTH;
    }
    if (height == 0) {
      height = DEFAULT_COOLBAR_HEIGHT;
    }
    if ((style & SWT.VERTICAL) != 0) {
      int tmp = width;
      width = height;
      height = tmp;
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    height += border * 2;
    width += border * 2;
    return new Point(width, height);
  }
}
