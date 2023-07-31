class PlaceHold {
  void createItem(TreeItem item, TreeItem parentItem, int index) {
    int count;
    TreeItem[] items;
    if (parentItem != null) {
      count = parentItem.itemCount;
      items = parentItem.items;
    } else {
      count = this.itemCount;
      items = this.items;
    }
    if (index == (-1)) {
      index = count;
    }
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_INVALID_RANGE);
    }
    if (count == items.length) {
      TreeItem[] newItems = new TreeItem[items.length + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
      if (parentItem != null) {
        parentItem.items = items;
      } else {
        this.items = items;
      }
    }
    System.arraycopy(items, index, items, index + 1, (count++) - index);
    items[index] = item;
    item.items = new TreeItem[4];
    SWTTreeItem handle = ((SWTTreeItem) (new SWTTreeItem().alloc().init()));
    item.handle = handle;
    item.createJNIRef();
    item.register();
    if (parentItem != null) {
      parentItem.itemCount = count;
    } else {
      this.itemCount = count;
    }
    ((NSTableView) (view)).reloadData();
  }
}
