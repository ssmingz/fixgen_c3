class CTabItem2 {
  public CTabItem2(CTabFolder2 parent, int style, int index) {
    super(parent, checkStyle(style));
    parent.createItem(this, index);
  }
}
