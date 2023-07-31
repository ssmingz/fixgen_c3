class PlaceHold {
  LRESULT WM_LBUTTONDBLCLK(int wParam, int lParam) {
    TVHITTESTINFO lpht = new TVHITTESTINFO();
    lpht.x = OS.GET_X_LPARAM(lParam);
    lpht.y = OS.GET_Y_LPARAM(lParam);
    OS.SendMessage(handle, TVM_HITTEST, 0, lpht);
    if (lpht.hItem != 0) {
      if ((style & SWT.CHECK) != 0) {
        if ((lpht.flags & OS.TVHT_ONITEMSTATEICON) != 0) {
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
          if ((!display.captureChanged) && (!isDisposed())) {
            if (OS.GetCapture() != handle) {
              OS.SetCapture(handle);
            }
          }
          OS.SetFocus(handle);
          TVITEM tvItem = new TVITEM();
          tvItem.hItem = lpht.hItem;
          tvItem.mask = (OS.TVIF_HANDLE | OS.TVIF_PARAM) | OS.TVIF_STATE;
          tvItem.stateMask = OS.TVIS_STATEIMAGEMASK;
          OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
          int state = tvItem.state >> 12;
          if ((state & 0x1) != 0) {
            state++;
          } else {
            --state;
          }
          tvItem.state = state << 12;
          OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
          if (!OS.IsWinCE) {
            int id = tvItem.hItem;
            if (OS.COMCTL32_MAJOR >= 6) {
              id = OS.SendMessage(handle, TVM_MAPHTREEITEMTOACCID, tvItem.hItem, 0);
            }
            OS.NotifyWinEvent(EVENT_OBJECT_FOCUS, handle, OBJID_CLIENT, id);
          }
          Event event = new Event();
          event.item = _getItem(tvItem.hItem, tvItem.lParam);
          event.detail = SWT.CHECK;
          postEvent(Selection, event);
          return LRESULT.ZERO;
        }
      }
    }
    LRESULT result = super.WM_LBUTTONDBLCLK(wParam, lParam);
    if (result == LRESULT.ZERO) {
      return result;
    }
    if (lpht.hItem != 0) {
      if (((style & SWT.FULL_SELECTION) != 0) || ((lpht.flags & OS.TVHT_ONITEM) != 0)) {
        Event event = new Event();
        event.item = _getItem(lpht.hItem);
        postEvent(DefaultSelection, event);
      }
    }
    return result;
  }
}
