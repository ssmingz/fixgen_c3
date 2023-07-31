class TreeItem {
  public TreeItem(Tree parent, int style) {
    super(parent, style);
    this.parent = parent;
    parent.createItem(this, null, -1);
  }
}
