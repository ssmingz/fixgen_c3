class PlaceHold {
  void redrawSelection() {
    if ((style & SWT.SINGLE) != 0) {
      int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
      if (hItem != 0) {
        RECT rect = new RECT();
        if (OS.TreeView_GetItemRect(handle, hItem, rect, false)) {
          OS.InvalidateRect(handle, rect, true);
        }
      }
    } else {
      int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0);
      if (hItem != 0) {
        TVITEM tvItem = null;
        if (OS.IsWinCE) {
          tvItem = new TVITEM();
          tvItem.mask = OS.TVIF_STATE;
        }
        RECT rect = new RECT();
        int index = 0;
        int count = ((int) (OS.SendMessage(handle, TVM_GETVISIBLECOUNT, 0, 0)));
        while ((index <= count) && (hItem != 0)) {
          int state = 0;
          if (OS.IsWinCE) {
            tvItem.hItem = hItem;
            OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
            state = tvItem.state;
          } else {
            state = ((int) (OS.SendMessage(handle, TVM_GETITEMSTATE, hItem, TVIS_SELECTED)));
          }
          if ((state & OS.TVIS_SELECTED) != 0) {
            if (OS.TreeView_GetItemRect(handle, hItem, rect, false)) {
              OS.InvalidateRect(handle, rect, true);
            }
          }
          hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_NEXTVISIBLE, hItem);
          index++;
        }
      }
    }
  }
}
