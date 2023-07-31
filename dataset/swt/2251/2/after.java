class PlaceHold {
  void createItem(TreeItem item, TreeItem parentItem, int index) {
    int count = 0;
    for (int i = 0; i < items.length; i++) {
      if ((items[i] != null) && (items[i].parentItem == parentItem)) {
        count++;
      }
    }
    if (index == (-1)) {
      index = count;
    }
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_INVALID_RANGE);
    }
    item.index = index;
    for (int i = 0; i < items.length; i++) {
      if ((items[i] != null) && (items[i].parentItem == parentItem)) {
        if (items[i].index >= item.index) {
          items[i].index++;
        }
      }
    }
    int id = 0;
    while ((id < items.length) && (items[id] != null)) {
      id++;
    }
    if (id == items.length) {
      TreeItem[] newItems = new TreeItem[items.length + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    items[id] = item;
    item.id = id + 1;
    int parentID = OS.kDataBrowserNoItem;
    boolean expanded = true;
    if (parentItem != null) {
      parentID = parentItem.id;
      expanded = parentItem.getExpanded();
    }
    if (expanded) {
      if (OS.AddDataBrowserItems(handle, parentID, 1, new int[] {item.id}, 0) != OS.noErr) {
        items[id] = null;
        error(ERROR_ITEM_NOT_ADDED);
      }
    }
  }
}
