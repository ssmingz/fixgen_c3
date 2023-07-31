class PlaceHold {
  LRESULT WM_LBUTTONDBLCLK(int wParam, int lParam) {
    LVHITTESTINFO pinfo = new LVHITTESTINFO();
    pinfo.x = ((short) (lParam & 0xffff));
    pinfo.y = ((short) (lParam >> 16));
    int index = OS.SendMessage(handle, LVM_HITTEST, 0, pinfo);
    sendMouseEvent(MouseDown, 1, handle, WM_LBUTTONDOWN, wParam, lParam);
    if (!sendMouseEvent(MouseDoubleClick, 1, handle, WM_LBUTTONDBLCLK, wParam, lParam)) {
      if (OS.GetCapture() != handle) {
        OS.SetCapture(handle);
      }
      return LRESULT.ZERO;
    }
    if (pinfo.iItem != (-1)) {
      callWindowProc(handle, WM_LBUTTONDBLCLK, wParam, lParam);
    }
    if (OS.GetCapture() != handle) {
      OS.SetCapture(handle);
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
