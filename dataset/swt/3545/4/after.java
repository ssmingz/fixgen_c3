class PlaceHold {
  LRESULT WM_RBUTTONDBLCLK(int wParam, int lParam) {
    LVHITTESTINFO pinfo = new LVHITTESTINFO();
    pinfo.x = OS.GET_X_LPARAM(lParam);
    pinfo.y = OS.GET_Y_LPARAM(lParam);
    OS.SendMessage(handle, LVM_HITTEST, 0, pinfo);
    Display display = this.display;
    display.captureChanged = false;
    sendMouseEvent(MouseDown, 3, handle, WM_RBUTTONDOWN, wParam, lParam);
    if (sendMouseEvent(MouseDoubleClick, 3, handle, WM_RBUTTONDBLCLK, wParam, lParam)) {
      if (pinfo.iItem != (-1)) {
        callWindowProc(handle, WM_RBUTTONDBLCLK, wParam, lParam);
      }
    }
    if ((!display.captureChanged) && (!isDisposed())) {
      if (OS.GetCapture() != handle) {
        OS.SetCapture(handle);
      }
    }
    return LRESULT.ZERO;
  }
}
