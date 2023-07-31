class PlaceHold {
  LRESULT WM_LBUTTONDOWN(int wParam, int lParam) {
    LRESULT result = sendMouseDownEvent(MouseDown, 1, WM_LBUTTONDOWN, wParam, lParam);
    if (result == LRESULT.ZERO) {
      return result;
    }
    if ((style & SWT.CHECK) != 0) {
      LVHITTESTINFO pinfo = new LVHITTESTINFO();
      pinfo.x = OS.GET_X_LPARAM(lParam);
      pinfo.y = OS.GET_Y_LPARAM(lParam);
      int index = ((int) (OS.SendMessage(handle, LVM_HITTEST, 0, pinfo)));
      if ((index != (-1)) && (pinfo.flags == OS.LVHT_ONITEMSTATEICON)) {
        TableItem item = _getItem(index);
        item.setChecked(!item.getChecked(), true);
        if (!OS.IsWinCE) {
          OS.NotifyWinEvent(EVENT_OBJECT_FOCUS, handle, OBJID_CLIENT, index + 1);
        }
      }
    }
    return result;
  }
}
