class PlaceHold {
  public void update() {
    checkWidget();
    int flags = OS.RDW_UPDATENOW | OS.RDW_ALLCHILDREN;
    OS.RedrawWindow(handle, null, 0, flags);
  }
}
