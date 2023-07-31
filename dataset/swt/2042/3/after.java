class PlaceHold {
  public void selectAll() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_STATE;
    tvItem.state = OS.TVIS_SELECTED;
    tvItem.stateMask = OS.TVIS_SELECTED;
    int oldProc = OS.GetWindowLong(handle, GWL_WNDPROC);
    OS.SetWindowLong(handle, GWL_WNDPROC, TreeProc);
    if ((style & SWT.VIRTUAL) != 0) {
      int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
      select(hItem, tvItem);
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
