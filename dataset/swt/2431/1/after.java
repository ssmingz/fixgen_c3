class PlaceHold {
  public void setRedraw(boolean redraw) {
    checkWidget();
    if (redraw) {
      if ((--drawCount) == 0) {
        OS.SendMessage(handle, WM_SETREDRAW, 1, 0);
        if (OS.IsWinCE) {
          OS.InvalidateRect(handle, null, true);
        } else {
          int flags = ((OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE) | OS.RDW_ALLCHILDREN;
          OS.RedrawWindow(handle, null, 0, flags);
        }
      }
    } else if ((drawCount++) == 0) {
      OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
    }
  }
}
