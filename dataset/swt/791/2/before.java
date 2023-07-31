class CTabItem {
  public CTabItem(CTabFolder parent, int style, int index) {
    super(parent, checkStyle(style));
    parent.createItem(this, index);
  }
}
