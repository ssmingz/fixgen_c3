class PlaceHold {
  void clearAll(TreeItem parentItem, boolean all) {
    int count = getItemCount(parentItem);
    if (count == 0) {
      return;
    }
    TreeItem[] children = (parentItem == null) ? items : parentItem.items;
    for (int i = 0; i < count; i++) {
      TreeItem item = children[i];
      if (item != null) {
        item.clear();
        ((NSOutlineView) (view)).reloadItem(item.handle);
        if (all) {
          clearAll(item, true);
        }
      }
    }
  }
}
