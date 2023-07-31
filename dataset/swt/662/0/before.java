class PlaceHold {
  void destroyItem(TreeItem item) {
    TreeItem parentItem = item.parentItem;
    if ((parentItem == null) || parentItem.getExpanded()) {
      int parentID = (parentItem == null) ? OS.kDataBrowserNoItem : item.parentItem.id;
      if (OS.RemoveDataBrowserItems(handle, parentID, 1, new int[] {item.id}, 0) != OS.noErr) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
    }
    releaseItems(item.getItems());
    releaseItem(item);
    boolean hasChild = false;
    for (int i = 0; i < items.length; i++) {
      if ((items[i] != null) && (items[i].parentItem == parentItem)) {
        if (items[i].index >= item.index) {
          --items[i].index;
          hasChild = true;
        }
      }
    }
    if ((hasChild && (parentItem != null)) && (!parentItem.getExpanded())) {
      parentItem.redraw(kDataBrowserNoItem);
    }
    setScrollWidth();
  }
}
