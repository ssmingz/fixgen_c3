class TreeItem {
  public TreeItem(Tree parent, int style) {
    this(checkNull(parent), null, style, -1, true);
  }
}
