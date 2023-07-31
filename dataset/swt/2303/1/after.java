class TreeItem {
  public TreeItem(TreeItem parentItem, int style, int index) {
    super(checkNull(parentItem).parent, style);
    if (index < 0) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    this.parent = parentItem.parent;
    if (!parent.createItem(this, parentItem.handle, index)) {
      error(ERROR_INVALID_RANGE);
    }
  }
}
