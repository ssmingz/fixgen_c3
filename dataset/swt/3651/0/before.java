class PlaceHold {
  boolean setScrollWidth() {
    if ((style & SWT.H_SCROLL) == 0) {
      return false;
    }
    if (items == null) {
      return false;
    }
    NSCell cell = column.dataCell();
    Font font = (this.font != null) ? this.font : defaultFont();
    cell.setFont(font.handle);
    float width = 0;
    for (int i = 0; i < itemCount; i++) {
      cell.setTitle(NSString.stringWith(items[i]));
      NSSize size = cell.cellSize();
      width = Math.max(width, size.width);
    }
    column.setWidth(width);
    return true;
  }
}
