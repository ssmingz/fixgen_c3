class CTabItem {
  public CTabItem(CTabFolder parent, int style, int index) {
    super(parent, checkStyle(style));
    showClose = (style & SWT.CLOSE) != 0;
    parent.createItem(this, index);
  }
}
