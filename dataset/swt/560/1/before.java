class PlaceHold {
  public void moveBelow(Control control) {
    checkWidget();
    int hwndAfter = OS.HWND_BOTTOM;
    if (control != null) {
      hwndAfter = control.handle;
    }
    if (hwndAfter == 0) {
      return;
    }
    int flags = (OS.SWP_NOSIZE | OS.SWP_NOMOVE) | OS.SWP_NOACTIVATE;
    OS.SetWindowPos(handle, hwndAfter, 0, 0, 0, 0, flags);
  }
}
