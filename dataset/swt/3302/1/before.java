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
        OS.SendMessage(handle, WM_SETREDRAW, 1, 0);
        int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
        if (hwndHeader != 0) {
          OS.SendMessage(hwndHeader, WM_SETREDRAW, 1, 0);
        }
        if ((state & HIDDEN) != 0) {
          state &= ~HIDDEN;
          OS.ShowWindow(handle, SW_HIDE);
        } else if (OS.IsWinCE) {
          OS.InvalidateRect(handle, null, false);
          if (hwndHeader != 0) {
            OS.InvalidateRect(hwndHeader, null, false);
          }
        } else {
          int flags = ((OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE) | OS.RDW_ALLCHILDREN;
          OS.RedrawWindow(handle, null, 0, flags);
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
