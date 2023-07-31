class PlaceHold {
  void clear(int hItem, TVITEM tvItem) {
    tvItem.hItem = hItem;
    TreeItem item = null;
    if (OS.SendMessage(handle, TVM_GETITEM, 0, tvItem) != 0) {
      item = (tvItem.lParam != (-1)) ? items[tvItem.lParam] : null;
    }
    if (item != null) {
      item.clear();
      item.redraw();
    }
  }
}
