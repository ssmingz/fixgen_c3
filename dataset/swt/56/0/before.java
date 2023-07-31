class PlaceHold {
  public void redraw() {
    checkWidget();
    if (!OS.IsWindowVisible(handle)) {
      return;
    }
    int flags = (OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE;
    OS.RedrawWindow(handle, null, 0, flags);
  }
}
