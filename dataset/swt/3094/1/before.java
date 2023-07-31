class TreeItem {
  public TreeItem(Tree parent, int style) {
    this(parent, style, checkNull(parent).getItemCount());
  }
}
