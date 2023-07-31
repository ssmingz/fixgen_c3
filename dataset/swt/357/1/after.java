class PlaceHold {
  public boolean getExpanded() {
    checkWidget();
    int hwnd = parent.handle;
    int state = 0;
    if (OS.IsWinCE) {
      TVITEM tvItem = new TVITEM();
      tvItem.hItem = handle;
      tvItem.mask = OS.TVIF_STATE;
      OS.SendMessage(hwnd, TVM_GETITEM, 0, tvItem);
      state = tvItem.state;
    } else {
      state = ((int) (OS.SendMessage(hwnd, TVM_GETITEMSTATE, handle, TVIS_EXPANDED)));
    }
    return (state & OS.TVIS_EXPANDED) != 0;
  }
}
