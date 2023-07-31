class PlaceHold {
  Point computeSize() {
    checkWidget();
    int width = 0;
    int height = 0;
    if ((style & SWT.SEPARATOR) != 0) {
      if ((parent.style & SWT.HORIZONTAL) != 0) {
        width = getWidth();
        height = DEFAULT_HEIGHT;
      } else {
        width = DEFAULT_WIDTH;
        height = getWidth();
      }
      if (control != null) {
        height = Math.max(height, control.getMininumHeight());
      }
    } else {
      if ((text.length() != 0) || (image != null)) {
        NSButton widget = ((NSButton) (button));
        NSSize size = widget.cell().cellSize();
        width = ((int) (Math.ceil(size.width)));
        height = ((int) (Math.ceil(size.height)));
      } else {
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
      }
      if ((style & SWT.DROP_DOWN) != 0) {
        width += ARROW_WIDTH + INSET;
      }
      width += INSET * 2;
      height += INSET * 2;
    }
    return new Point(width, height);
  }
}
