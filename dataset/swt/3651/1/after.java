class PlaceHold {
  boolean setScrollWidth(String item) {
    if ((style & SWT.H_SCROLL) == 0) {
      return false;
    }
    NSCell cell = column.dataCell();
    Font font = (this.font != null) ? this.font : defaultFont();
    cell.setFont(font.handle);
    cell.setTitle(NSString.stringWith(item));
    NSSize size = cell.cellSize();
    double oldWidth = column.width();
    if (oldWidth < size.width) {
      column.setWidth(size.width);
      return true;
    }
    return false;
  }
}
