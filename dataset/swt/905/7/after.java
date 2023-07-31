class PlaceHold {
  public void clear(int index, boolean all) {
    checkWidget();
    long hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
    if (hItem == 0) {
      error(ERROR_INVALID_RANGE);
    }
    hItem = findItem(hItem, index);
    if (hItem == 0) {
      error(ERROR_INVALID_RANGE);
    }
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_PARAM;
    clear(hItem, tvItem);
    if (all) {
      hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CHILD, hItem);
      clearAll(hItem, tvItem, all);
    }
  }
}
