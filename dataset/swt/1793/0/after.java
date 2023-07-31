class PlaceHold {
  public void setRedraw(boolean redraw) {
    checkWidget();
    if (drawCount == 0) {
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      if ((bits & OS.WS_VISIBLE) == 0) {
        state |= HIDDEN;
      }
    }
    if (redraw) {
      if ((--drawCount) == 0) {
        setScrollWidth(null, true);
        ignoreResize = true;
        OS.SendMessage(handle, WM_SETREDRAW, 1, 0);
        if ((state & HIDDEN) != 0) {
          state &= ~HIDDEN;
          OS.ShowWindow(handle, SW_HIDE);
        }
        if (!ignoreResize) {
          setResizeChildren(false);
          sendEvent(Resize);
          if (isDisposed()) {
            return;
          }
          if (layout != null) {
            layout.layout(this, false);
          }
          setResizeChildren(true);
        }
        ignoreResize = false;
        if (OS.IsWinCE) {
          OS.InvalidateRect(handle, null, false);
        } else {
          OS.RedrawWindow(handle, null, 0, OS.RDW_FRAME | OS.RDW_INVALIDATE);
        }
      }
    } else if ((drawCount++) == 0) {
      OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
      int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
      if (hwndHeader != 0) {
        OS.SendMessage(hwndHeader, WM_SETREDRAW, 0, 0);
      }
    }
  }
}
