class MenuItem {
  public MenuItem(Menu parent, int style, int index) {
    super(parent, checkStyle(style));
    this.parent = parent;
    int count = parent.getItemCount();
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_INVALID_RANGE);
    }
    createWidget(index);
  }
}
