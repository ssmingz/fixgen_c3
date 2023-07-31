class PlaceHold {
  public TreeItem getItem(int index) {
    checkWidget();
    if (index < 0) {
      error(ERROR_INVALID_RANGE);
    }
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    int hwnd = parent.handle;
    int hFirstItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_CHILD, handle);
    if (hFirstItem == 0) {
      error(ERROR_INVALID_RANGE);
    }
    int hItem = parent.findItem(hFirstItem, index);
    if (hItem == 0) {
      error(ERROR_INVALID_RANGE);
    }
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_PARAM;
    tvItem.hItem = hItem;
    OS.SendMessage(hwnd, TVM_GETITEM, 0, tvItem);
    return parent._getItem(tvItem.hItem, tvItem.lParam);
  }
}
