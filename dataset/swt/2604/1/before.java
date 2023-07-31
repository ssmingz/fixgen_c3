class PlaceHold {
  void setParent() {
    int hwndParent = parent.handle;
    display.lockActiveWindow = true;
    OS.SetParent(handle, hwndParent);
    if (!OS.IsWindowVisible(hwndParent)) {
      OS.ShowWindow(handle, SW_SHOWNA);
    }
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    bits &= ~OS.WS_CHILD;
    OS.SetWindowLong(handle, GWL_STYLE, bits | OS.WS_POPUP);
    OS.SetWindowLongPtr(handle, GWLP_ID, 0);
    int flags = (OS.SWP_NOSIZE | OS.SWP_NOMOVE) | OS.SWP_NOACTIVATE;
    SetWindowPos(handle, HWND_BOTTOM, 0, 0, 0, 0, flags);
    display.lockActiveWindow = false;
  }
}
