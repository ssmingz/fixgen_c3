class PlaceHold {
  LRESULT WM_RBUTTONDOWN(int wParam, int lParam) {
    Display display = this.display;
    display.captureChanged = false;
    if (!sendMouseEvent(MouseDown, 3, handle, WM_RBUTTONDOWN, wParam, lParam)) {
      if ((!display.captureChanged) && (!isDisposed())) {
        if (OS.GetCapture() != handle) {
          OS.SetCapture(handle);
        }
      }
      return LRESULT.ZERO;
    }
    setFocus();
    TVHITTESTINFO lpht = new TVHITTESTINFO();
    lpht.x = OS.GET_X_LPARAM(lParam);
    lpht.y = OS.GET_Y_LPARAM(lParam);
    OS.SendMessage(handle, TVM_HITTEST, 0, lpht);
    if (lpht.hItem != 0) {
      int flags = OS.TVHT_ONITEMICON | OS.TVHT_ONITEMLABEL;
      if (((style & SWT.FULL_SELECTION) != 0) || ((lpht.flags & flags) != 0)) {
        if ((wParam & (OS.MK_CONTROL | OS.MK_SHIFT)) == 0) {
          TVITEM tvItem = new TVITEM();
          tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_STATE;
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
