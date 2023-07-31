class TreeColumn {
  public TreeColumn(Tree2 parent, int style, int index) {
    super(parent, checkStyle(style), index);
    if (!((0 <= index) && (index <= parent.getColumnCount()))) {
      error(ERROR_INVALID_RANGE);
    }
    this.parent = parent;
    parent.addColumn(this, index);
  }
}
