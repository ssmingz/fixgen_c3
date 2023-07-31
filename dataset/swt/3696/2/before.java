class PlaceHold {
  public void setAlignment(int alignment) {
    checkWidget();
    if ((style & alignment) != 0) {
      return;
    }
    style &= ~((SWT.LEFT | SWT.CENTER) | SWT.RIGHT);
    style |= alignment;
    parent.redraw(x, 0, width, parent.getClientArea().height, true);
  }
}
