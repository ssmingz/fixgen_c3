class TreeItem {
  public TreeItem(Tree parent, int style, int index) {
    super(parent, style);
    if (index < 0) {
      error(ERROR_INVALID_RANGE);
    }
    this.parent = parent;
    parent.createItem(this, 0, index);
  }
}
