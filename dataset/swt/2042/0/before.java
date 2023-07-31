class PlaceHold {
  public void deselectAll() {
    checkWidget();
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_STATE;
    tvItem.stateMask = OS.TVIS_SELECTED;
    if ((style & SWT.SINGLE) != 0) {
      int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
      if (hItem != 0) {
        tvItem.hItem = hItem;
        OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
      }
    } else {
      int oldProc = OS.GetWindowLong(handle, GWL_WNDPROC);
      OS.SetWindowLong(handle, GWL_WNDPROC, TreeProc);
      if ((style & SWT.VIRTUAL) != 0) {
        int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
        deselect(hItem, tvItem, 0);
      } else {
        for (int i = 0; i < items.length; i++) {
          TreeItem item = items[i];
          if (item != null) {
            tvItem.hItem = item.handle;
            OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
          }
        }
      }
      OS.SetWindowLong(handle, GWL_WNDPROC, oldProc);
    }
  }
}
