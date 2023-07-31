class ToolItem {
  public ToolItem(ToolBar parent, int style, int index) {
    super(parent, checkStyle(style));
    this.parent = parent;
    parent.createItem(this, index);
  }
}
