class TreeItem2 {
  public TreeItem2(TreeItem2 parentItem, int style, int index) {
    super(checkNull(parentItem).parent, style);
    this.parentItem = parentItem;
    parent = parentItem.getParent();
    parentItem.addItem(this, index);
  }
}
