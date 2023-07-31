class TreeItem {
  public TreeItem(TreeItem parentItem, int style) {
    this(parentItem, style, checkNull(parentItem).getItemCount());
  }
}
