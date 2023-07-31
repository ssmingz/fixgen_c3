class PlaceHold {
  LRESULT WM_RBUTTONDOWN(int wParam, int lParam) {
    sendMouseEvent(MouseDown, 3, handle, WM_RBUTTONDOWN, wParam, lParam);
    setFocus();
    TVHITTESTINFO lpht = new TVHITTESTINFO();
    lpht.x = ((short) (lParam & 0xffff));
    lpht.y = ((short) (lParam >> 16));
    OS.SendMessage(handle, TVM_HITTEST, 0, lpht);
    if (lpht.hItem != 0) {
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      int flags = OS.TVHT_ONITEMICON | OS.TVHT_ONITEMLABEL;
      if (((bits & OS.TVS_FULLROWSELECT) != 0) || ((lpht.flags & flags) != 0)) {
        if ((wParam & (OS.MK_CONTROL | OS.MK_SHIFT)) == 0) {
          TVITEM tvItem = new TVITEM();
          tvItem.mask = OS.TVIF_STATE;
          tvItem.stateMask = OS.TVIS_SELECTED;
          tvItem.hItem = lpht.hItem;
          OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
          if ((tvItem.state & OS.TVIS_SELECTED) == 0) {
            ignoreSelect = true;
            OS.SendMessage(handle, TVM_SELECTITEM, TVGN_CARET, 0);
            ignoreSelect = false;
            OS.SendMessage(handle, TVM_SELECTITEM, TVGN_CARET, lpht.hItem);
          }
        }
      }
    }
    return LRESULT.ZERO;
  }
}
