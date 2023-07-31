class PlaceHold {
  public void moveAbove(Control control) {
    checkWidget();
    int hwndAfter = OS.HWND_TOP;
    if (control != null) {
      if (control.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      int hwnd = control.handle;
      if ((hwnd == 0) || (hwnd == handle)) {
        return;
      }
      hwndAfter = OS.GetWindow(hwnd, GW_HWNDPREV);
      if (hwndAfter == 0) {
        hwndAfter = OS.HWND_TOP;
      }
    }
    int flags = (OS.SWP_NOSIZE | OS.SWP_NOMOVE) | OS.SWP_NOACTIVATE;
    OS.SetWindowPos(handle, hwndAfter, 0, 0, 0, 0, flags);
  }
}
