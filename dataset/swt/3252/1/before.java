class TreeItem2 {
  public TreeItem2(Tree2 parent, int style, int index) {
    super(parent, style);
    this.parent = parent;
    this.index = index;
    initialize();
    parent.addItem(this);
  }
}
