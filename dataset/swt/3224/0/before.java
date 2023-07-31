class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    NSControl widget = ((NSControl) (view));
    NSCell viewCell = widget.cell();
    NSSize size = viewCell.cellSize();
    width = ((int) (Math.ceil(size.width)));
    height = ((int) (Math.ceil(size.height)));
    if ((style & SWT.READ_ONLY) == 0) {
      NSComboBoxCell cell = new NSComboBoxCell(viewCell.id);
      NSArray array = cell.objectValues();
      int length = ((int) (array.count()));
      if (length > 0) {
        cell = new NSComboBoxCell(cell.copy());
        for (int i = 0; i < length; i++) {
          id object = array.objectAtIndex(i);
          cell.setTitle(new NSString(object));
          size = cell.cellSize();
          width = Math.max(width, ((int) (Math.ceil(size.width))));
        }
        cell.release();
      }
    }
    if (hHint != SWT.DEFAULT) {
      if (((style & SWT.READ_ONLY) != 0) || (hHint < height)) {
        height = hHint;
      }
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    return new Point(width, height);
  }
}
