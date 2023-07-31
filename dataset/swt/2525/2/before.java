class Sash {
  public Sash(Composite parent, int style) {
    super(parent, checkStyle(style));
    int cursorStyle = ((style & SWT.VERTICAL) != 0) ? SWT.CURSOR_SIZEWE : SWT.CURSOR_SIZENS;
    sizeCursor = new Cursor(getDisplay(), cursorStyle);
  }
}
