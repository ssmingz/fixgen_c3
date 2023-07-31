class PlaceHold {
  void paint(GC gc, int itemIndex) {
    gc.setFont(parent.getFont());
    Rectangle bounds = getBounds(itemIndex);
    gc.fillRectangle(bounds.x, bounds.y + 1, bounds.width, bounds.height - 3);
    if (itemIndex != TableColumn.FILL) {
      int extent = Math.min(bounds.width - (2 * HORIZONTAL_MARGIN), getPreferredWidth(itemIndex));
      int x = bounds.x;
      int alignment = parent.internalGetColumn(itemIndex).getAlignment();
      if ((alignment & SWT.CENTER) != 0) {
        x += (bounds.width - extent) / 2;
      } else if ((alignment & SWT.RIGHT) != 0) {
        x += (bounds.width - extent) - HORIZONTAL_MARGIN;
      } else {
        x += HORIZONTAL_MARGIN;
      }
      Image image = getImage(itemIndex);
      if (image != null) {
        Rectangle imageBounds = image.getBounds();
        Point imageExtent = getImageExtent();
        int y = bounds.y + ((bounds.height - imageExtent.y) / 2);
        gc.drawImage(
            image, 0, 0, imageBounds.width, imageBounds.height, x, y, imageExtent.x, imageExtent.y);
        x += imageExtent.x + HORIZONTAL_MARGIN;
      }
      String label = getText(itemIndex);
      if (label != null) {
        int maxWidth = ((bounds.x + bounds.width) - x) - HORIZONTAL_MARGIN;
        String trimLabel = parent.trimItemText(label, maxWidth, gc);
        Point textExtent = gc.stringExtent(trimLabel);
        int y = bounds.y + ((bounds.height - textExtent.y) / 2);
        gc.drawString(trimLabel, x, y);
      }
    }
    drawHighlightShadow(gc, itemIndex);
    drawLowlightShadows(gc, itemIndex);
  }
}
