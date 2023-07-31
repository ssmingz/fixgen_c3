class PlaceHold {
  public void moveBelow(Control control) {
    checkWidget();
    int hwndAbove = OS.HWND_BOTTOM;
    if (control != null) {
      if (control.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      hwndAbove = control.handle;
    }
    if ((hwndAbove == 0) || (hwndAbove == handle)) {
      return;
    }
    int flags = (OS.SWP_NOSIZE | OS.SWP_NOMOVE) | OS.SWP_NOACTIVATE;
    OS.SetWindowPos(handle, hwndAbove, 0, 0, 0, 0, flags);
  }
}
