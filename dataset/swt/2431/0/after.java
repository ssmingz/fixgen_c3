class PlaceHold {
  public void redraw() {
    checkWidget();
    if (!OS.IsWindowVisible(handle)) {
      return;
    }
    if (OS.IsWinCE) {
      OS.InvalidateRect(handle, null, true);
    } else {
      int flags = (OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE;
      OS.RedrawWindow(handle, null, 0, flags);
    }
  }
}
