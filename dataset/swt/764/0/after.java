class TreeItem2 {
  public TreeItem2(Tree2 parent, int style, int index) {
    super(parent, style);
    int validItemIndex = parent.getItemCount();
    if (!((0 <= index) && (index <= validItemIndex))) {
      error(ERROR_INVALID_RANGE);
    }
    this.parent = parent;
    initialize();
    parent.addItem(this, index);
  }
}
