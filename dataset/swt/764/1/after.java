class TreeItem2 {
  public TreeItem2(TreeItem2 parentItem, int style, int index) {
    super(checkNull(parentItem).parent, style);
    this.parentItem = parentItem;
    parent = parentItem.getParent();
    int validItemIndex = parentItem.getItemCount();
    if (!((0 <= index) && (index <= validItemIndex))) {
      error(ERROR_INVALID_RANGE);
    }
    parentItem.addItem(this, index);
  }
}
