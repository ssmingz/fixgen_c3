class PlaceHold {
  void drawInteriorWithFrame_inView(int id, int sel, int cellFrame, int view) {
    NSRect cellRect = new NSRect();
    OS.memmove(cellRect, cellFrame, sizeof);
    NSGraphicsContext context = NSGraphicsContext.currentContext();
    context.saveGraphicsState();
    int contentWidth = 0;
    NSSize stringSize = null;
    NSSize imageSize = null;
    NSAttributedString attrString = null;
    if (displayText != null) {
      NSString string = NSString.stringWith(displayText);
      attrString =
          ((NSAttributedString) (new NSAttributedString().alloc())).initWithString(string, null);
      stringSize = attrString.size();
      contentWidth += stringSize.width;
      if (image != null) {
        contentWidth += MARGIN;
      }
    }
    if (image != null) {
      imageSize = handle.size();
      contentWidth += imageSize.width;
    }
    if ((parent.sortColumn == this) && (parent.sortDirection != SWT.NONE)) {
      boolean ascending = parent.sortDirection == SWT.UP;
      NSTableHeaderCell headerCell = nsColumn.headerCell();
      headerCell.drawSortIndicatorWithFrame(cellRect, new NSView(view), ascending, 0);
      NSRect sortRect = headerCell.sortIndicatorRectForBounds(cellRect);
      cellRect.width = Math.max(0, sortRect.x - cellRect.x);
    }
    int drawX = 0;
    if ((style & SWT.CENTER) != 0) {
      drawX = ((int) (cellRect.x + Math.max(MARGIN, (cellRect.width - contentWidth) / 2)));
    } else if ((style & SWT.RIGHT) != 0) {
      drawX = ((int) (cellRect.x + Math.max(MARGIN, (cellRect.width - contentWidth) - MARGIN)));
    } else {
      drawX = ((int) (cellRect.x)) + MARGIN;
    }
    if (image != null) {
      NSRect destRect = new NSRect();
      destRect.x = drawX;
      destRect.y = cellRect.y;
      destRect.width = Math.min(imageSize.width, cellRect.width - (2 * MARGIN));
      destRect.height = Math.min(imageSize.height, cellRect.height);
      boolean isFlipped = new NSView(view).isFlipped();
      if (isFlipped) {
        context.saveGraphicsState();
        NSAffineTransform transform = NSAffineTransform.transform();
        transform.scaleXBy(1, -1);
        transform.translateXBy(0, -(destRect.height + (2 * destRect.y)));
        transform.concat();
      }
      NSRect sourceRect = new NSRect();
      sourceRect.width = destRect.width;
      sourceRect.height = destRect.height;
      handle.drawInRect(destRect, sourceRect, NSCompositeSourceOver, 1.0F);
      if (isFlipped) {
        context.restoreGraphicsState();
      }
      drawX += destRect.width;
    }
    if ((displayText != null) && (displayText.length() > 0)) {
      if (image != null) {
        drawX += MARGIN;
      }
      NSRect destRect = new NSRect();
      destRect.x = drawX;
      destRect.y = cellRect.y;
      destRect.width = Math.min(stringSize.width, ((cellRect.x + cellRect.width) - MARGIN) - drawX);
      destRect.height = Math.min(stringSize.height, cellRect.height);
      attrString.drawInRect(destRect);
      attrString.release();
    }
    context.restoreGraphicsState();
  }
}
