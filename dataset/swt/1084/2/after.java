class PlaceHold {
  LRESULT WM_LBUTTONDBLCLK(int wParam, int lParam) {
    LVHITTESTINFO pinfo = new LVHITTESTINFO();
    pinfo.x = OS.GET_X_LPARAM(lParam);
    pinfo.y = OS.GET_Y_LPARAM(lParam);
    int index = OS.SendMessage(handle, LVM_HITTEST, 0, pinfo);
    Display display = this.display;
    display.captureChanged = false;
    sendMouseEvent(MouseDown, 1, handle, WM_LBUTTONDOWN, wParam, lParam);
    if (!sendMouseEvent(MouseDoubleClick, 1, handle, WM_LBUTTONDBLCLK, wParam, lParam)) {
      if ((!display.captureChanged) && (!isDisposed())) {
        if (OS.GetCapture() != handle) {
          OS.SetCapture(handle);
        }
      }
      return LRESULT.ZERO;
    }
    if (pinfo.iItem != (-1)) {
      callWindowProc(handle, WM_LBUTTONDBLCLK, wParam, lParam);
    }
    if ((!display.captureChanged) && (!isDisposed())) {
      if (OS.GetCapture() != handle) {
        OS.SetCapture(handle);
      }
    }
    if ((style & SWT.CHECK) != 0) {
      if ((index != (-1)) && (pinfo.flags == OS.LVHT_ONITEMSTATEICON)) {
        TableItem item = _getItem(index);
        item.setChecked(!item.getChecked(), true);
        if (!OS.IsWinCE) {
          OS.NotifyWinEvent(EVENT_OBJECT_FOCUS, handle, OBJID_CLIENT, index + 1);
        }
      }
    }
    return LRESULT.ZERO;
  }
}
