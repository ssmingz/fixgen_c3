class PlaceHold {
  public void clearAll(boolean all) {
    checkWidget();
    int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
    if (hItem == 0) {
      return;
    }
    if (all) {
      boolean redraw = false;
      for (int i = 0; i < items.length; i++) {
        TreeItem item = items[i];
        if ((item != null) && (item != currentItem)) {
          item.clear();
          redraw = true;
        }
      }
      if (redraw) {
        OS.InvalidateRect(handle, null, true);
      }
    } else {
      TVITEM tvItem = new TVITEM();
      tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_PARAM;
      clearAll(hItem, tvItem, all);
    }
  }
}
