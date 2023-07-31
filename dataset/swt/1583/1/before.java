class PlaceHold {
  public void setExpanded(boolean expanded) {
    checkWidget();
    int hwnd = parent.handle;
    int hOldItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_CARET, 0);
    parent.ignoreExpand = true;
    OS.SendMessage(hwnd, TVM_EXPAND, expanded ? OS.TVE_EXPAND : OS.TVE_COLLAPSE, handle);
    parent.ignoreExpand = false;
    int hNewItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_CARET, 0);
    if (hNewItem != hOldItem) {
      Event event = new Event();
      if (hNewItem != 0) {
        TVITEM tvItem = new TVITEM();
        tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_PARAM;
        tvItem.hItem = hNewItem;
        if (OS.SendMessage(hwnd, TVM_GETITEM, 0, tvItem) != 0) {
          event.item = parent.items[tvItem.lParam];
        }
        parent.hAnchor = hNewItem;
      }
      parent.sendEvent(Selection, event);
    }
  }
}
