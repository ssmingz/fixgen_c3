class PlaceHold {
  void clear(TreeItem parentItem, int index, boolean all) {
    TreeItem item = _getItem(parentItem, index, false);
    if (item != null) {
      item.clear();
      ((NSOutlineView) (view)).reloadItem(item.handle);
      if (all) {
        clearAll(item, true);
      }
    }
  }
}
