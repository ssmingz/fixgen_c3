class PlaceHold {
  public void pack() {
    checkWidget();
    int width = 0;
    if (displayText != null) {
      NSTableHeaderCell headerCell = nsColumn.headerCell();
      Font font = Font.cocoa_new(display, headerCell.font());
      NSAttributedString attrString = parent.createString(displayText, font, null, 0, true, false);
      NSSize stringSize = attrString.size();
      attrString.release();
      width += Math.ceil(stringSize.width);
      if (image != null) {
        width += MARGIN;
      }
    }
    if (image != null) {
      NSSize imageSize = handle.size();
      width += Math.ceil(imageSize.width);
    }
    if ((parent.sortColumn == this) && (parent.sortDirection != SWT.NONE)) {
      NSTableHeaderCell headerCell = nsColumn.headerCell();
      NSRect rect = new NSRect();
      rect.width = rect.height = Float.MAX_VALUE;
      NSSize cellSize = headerCell.cellSizeForBounds(rect);
      rect.height = cellSize.height;
      NSRect sortRect = headerCell.sortIndicatorRectForBounds(rect);
      width += Math.ceil(sortRect.width);
    }
    GC gc = new GC(parent);
    int index = parent.indexOf(this);
    width = Math.max(width, parent.calculateWidth(parent.items, index, gc));
    gc.dispose();
    setWidth(width);
  }
}
