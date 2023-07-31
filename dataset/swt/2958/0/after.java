class PlaceHold {
  public void setAlignment(int alignment) {
    checkWidget();
    if ((alignment & ((SWT.LEFT | SWT.RIGHT) | SWT.CENTER)) == 0) {
      return;
    }
    alignment = checkBits(alignment, LEFT, CENTER, RIGHT, 0, 0, 0);
    if ((style & alignment) != 0) {
      return;
    }
    style &= ~((SWT.LEFT | SWT.CENTER) | SWT.RIGHT);
    style |= alignment;
    if (getOrderIndex() == 0) {
      return;
    }
    int x = getX();
    parent.redraw(x, 0, width, parent.clientArea.height, false);
    if ((parent.drawCount == 0) && parent.getHeaderVisible()) {
      parent.header.redraw(x, 0, width, parent.getHeaderHeight(), false);
    }
  }
}
