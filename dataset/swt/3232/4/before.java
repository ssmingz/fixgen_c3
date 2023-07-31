class ToolItem {
  public ToolItem(ToolBar parent, int style, int index) {
    super(parent, checkStyle(style));
    this.parent = parent;
    int count = parent.getItemCount();
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    createWidget(index);
  }
}
