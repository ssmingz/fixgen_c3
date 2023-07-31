class PlaceHold {
  public void setAlignment(int alignment) {
    checkWidget();
    if (getIndex() == 0) {
      return;
    }
    if ((style & alignment) != 0) {
      return;
    }
    style &= ~((SWT.LEFT | SWT.CENTER) | SWT.RIGHT);
    style |= alignment;
    int x = getX();
    parent.redraw(x, 0, width, parent.getClientArea().height, false);
    if (parent.getHeaderVisible()) {
      parent.header.redraw(x, 0, width, parent.getHeaderHeight(), false);
    }
  }
}
