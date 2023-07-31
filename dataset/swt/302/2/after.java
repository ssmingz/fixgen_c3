class PlaceHold {
  public void moveAbove(Control control) {
    checkWidget();
    int hwndAbove = OS.HWND_TOP;
    if (control != null) {
      if (control.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      if (parent != control.parent) {
        return;
      }
      int hwnd = control.handle;
      if ((hwnd == 0) || (hwnd == handle)) {
        return;
      }
      hwndAbove = OS.GetWindow(hwnd, GW_HWNDPREV);
      if ((hwndAbove == 0) || (hwndAbove == hwnd)) {
        hwndAbove = OS.HWND_TOP;
      }
    }
    int flags = (OS.SWP_NOSIZE | OS.SWP_NOMOVE) | OS.SWP_NOACTIVATE;
    SetWindowPos(handle, hwndAbove, 0, 0, 0, 0, flags);
  }
}
