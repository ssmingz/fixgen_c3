class TreeItem {
  public TreeItem(Tree parent, int style, int index) {
    super(parent, style);
    int validItemIndex = parent.getItemCount();
    if (!((0 <= index) && (index <= validItemIndex))) {
      error(ERROR_INVALID_RANGE);
    }
    this.parent = parent;
    parent.createItem(this, index);
    int validColumnCount = Math.max(1, parent.getColumnCount());
    if (validColumnCount > 1) {
      texts = new String[validColumnCount];
      textWidths = new int[validColumnCount];
      images = new Image[validColumnCount];
    }
  }
}
