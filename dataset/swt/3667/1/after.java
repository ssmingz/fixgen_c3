class PlaceHold {
  public void update() {
    checkWidget();
    if (OS.IsWinCE) {
      OS.UpdateWindow(handle);
    } else {
      int flags = OS.RDW_UPDATENOW | OS.RDW_ALLCHILDREN;
      OS.RedrawWindow(handle, null, 0, flags);
    }
  }
}
