class Text {
  public Text(Composite parent, int style) {
    super(parent, checkStyle(style));
    if ((style & SWT.SEARCH) != 0) {
      if ((style & SWT.CANCEL) != 0) {
        this.style |= SWT.CANCEL;
      }
      if ((style & SWT.CANCEL) == 0) {
        NSSearchFieldCell cell = new NSSearchFieldCell(((NSSearchField) (view)).cell());
        cell.setCancelButtonCell(null);
      }
    }
  }
}
