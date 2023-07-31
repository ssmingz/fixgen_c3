class ToolItem {
  public ToolItem(ToolBar parent, int style) {
    super(parent, checkStyle(style));
    this.parent = parent;
    parent.createItem(this, parent.getItemCount());
    parent.relayout();
  }
}
