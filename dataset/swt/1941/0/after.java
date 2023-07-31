class PlaceHold {
  public void removeAll() {
    checkWidget();
    int hwnd = parent.handle;
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_PARAM;
    tvItem.hItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_CHILD, handle);
    while (tvItem.hItem != 0) {
      OS.SendMessage(hwnd, TVM_GETITEM, 0, tvItem);
      TreeItem item = (tvItem.lParam != (-1)) ? parent.items[((int) (tvItem.lParam))] : null;
      if ((item != null) && (!item.isDisposed())) {
        item.dispose();
      } else {
        parent.releaseItem(tvItem.hItem, tvItem, false);
        parent.destroyItem(null, tvItem.hItem);
      }
      tvItem.hItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_CHILD, handle);
    }
  }
}
