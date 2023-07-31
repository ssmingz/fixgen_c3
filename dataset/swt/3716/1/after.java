class TreeColumn {
  public TreeColumn(Tree parent, int style, int index) {
    super(parent, checkStyle(style), index);
    if (!((0 <= index) && (index <= parent.getColumnCount()))) {
      error(ERROR_INVALID_RANGE);
    }
    this.parent = parent;
    parent.createItem(this, index);
  }
}
