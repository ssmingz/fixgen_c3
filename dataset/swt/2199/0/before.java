class TreeItem {
  public TreeItem(TreeItem parentItem, int swtStyle) {
    this(parentItem, swtStyle, checkNull(parentItem).getItemCount());
  }
}
