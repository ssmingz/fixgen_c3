class PlaceHold {
  public void selectAll() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
    if (hItem == 0) {
      hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
      if (hItem != 0) {
        boolean fixScroll = checkScroll(hItem);
        if (fixScroll) {
          OS.SendMessage(handle, WM_SETREDRAW, 1, 0);
        }
        ignoreSelect = true;
        OS.SendMessage(handle, TVM_SELECTITEM, TVGN_CARET, hItem);
        ignoreSelect = false;
        if (fixScroll) {
          OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
        }
      }
    }
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_STATE;
    tvItem.state = OS.TVIS_SELECTED;
    tvItem.stateMask = OS.TVIS_SELECTED;
    int oldProc = OS.GetWindowLong(handle, GWL_WNDPROC);
    OS.SetWindowLong(handle, GWL_WNDPROC, TreeProc);
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if (item != null) {
        tvItem.hItem = item.handle;
        OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
      }
    }
    OS.SetWindowLong(handle, GWL_WNDPROC, oldProc);
  }
}
