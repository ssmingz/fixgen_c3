class TreeColumn {
  public TreeColumn(Tree parent, int style) {
    this(parent, style, checkNull(parent).getColumnCount());
  }
}
