class PlaceHold {
  void clear(TreeItem parentItem, int index, boolean all) {
    TreeItem item = _getItem(parentItem, index, false);
    if (item != null) {
      item.clear();
      item.redraw(-1);
      if (all) {
        clearAll(item, true);
      }
    }
  }
}
