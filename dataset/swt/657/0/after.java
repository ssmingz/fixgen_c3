class PlaceHold {
  LRESULT WM_LBUTTONDOWN(int wParam, int lParam) {
    mouseDown = true;
    LRESULT result = sendMouseDownEvent(MouseDown, 1, WM_LBUTTONDOWN, wParam, lParam);
    if ((style & SWT.CHECK) != 0) {
      LVHITTESTINFO pinfo = new LVHITTESTINFO();
      pinfo.x = ((short) (lParam & 0xffff));
      pinfo.y = ((short) (lParam >> 16));
      int index = OS.SendMessage(handle, LVM_HITTEST, 0, pinfo);
      if ((index != (-1)) && (pinfo.flags == OS.LVHT_ONITEMSTATEICON)) {
        TableItem item = _getItem(index);
        item.setChecked(!item.getChecked(), true);
      }
    }
    return result;
  }
}
