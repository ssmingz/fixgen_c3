class CTabItem2 {
  public CTabItem2(CTabFolder2 parent, int style, int index) {
    super(parent, checkStyle(style));
    showClose = (style & SWT.CLOSE) != 0;
    parent.createItem(this, index);
  }
}
