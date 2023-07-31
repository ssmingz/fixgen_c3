class PlaceHold {
  int calculateWidth(int index, GC gc) {
    if ((index == 0) && (width != (-1))) {
      return width;
    }
    Font font = null;
    if (cellFont != null) {
      font = cellFont[index];
    }
    if (font == null) {
      font = this.font;
    }
    if (font == null) {
      font = parent.font;
    }
    if (font == null) {
      font = parent.defaultFont();
    }
    String text = (index == 0) ? this.text : strings == null ? "" : strings[index];
    Image image = (index == 0) ? this.image : images == null ? null : images[index];
    NSCell cell = parent.dataCell;
    if (font.extraTraits != 0) {
      NSAttributedString attribStr = parent.createString(text, font, null, 0, true, false);
      cell.setAttributedStringValue(attribStr);
      attribStr.release();
    } else {
      cell.setFont(font.handle);
      cell.setTitle(NSString.stringWith(text != null ? text : ""));
    }
    objc_super super_struct = new objc_super();
    super_struct.receiver = cell.id;
    super_struct.super_class = OS.objc_msgSend(cell.id, sel_superclass);
    NSSize size = new NSSize();
    OS.objc_msgSendSuper_stret(size, super_struct, sel_cellSize);
    if (image != null) {
      size.width += parent.imageBounds.width + Tree.IMAGE_GAP;
    }
    int width = ((int) (Math.ceil(size.width)));
    boolean sendMeasure = true;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      sendMeasure = cached;
    }
    if (sendMeasure && parent.hooks(MeasureItem)) {
      gc.setFont(font);
      Event event = new Event();
      event.item = this;
      event.index = index;
      event.gc = gc;
      NSTableView widget = ((NSTableView) (parent.view));
      int height = ((int) (widget.rowHeight()));
      event.width = width;
      event.height = height;
      parent.sendEvent(MeasureItem, event);
      if (height < event.height) {
        widget.setRowHeight(event.height);
        widget.setNeedsDisplay(true);
      }
      width = event.width;
    }
    if (index == 0) {
      NSOutlineView outlineView = ((NSOutlineView) (parent.view));
      width += outlineView.indentationPerLevel() * (1 + outlineView.levelForItem(handle));
      this.width = width;
    }
    return width;
  }
}
