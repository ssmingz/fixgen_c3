class PlaceHold {
  void paint(GC gc) {
    int padding = parent.getHeaderPadding();
    int x = getX();
    int startX = x + padding;
    if ((style & SWT.LEFT) == 0) {
      int contentWidth = getContentWidth(gc, true);
      if ((style & SWT.RIGHT) != 0) {
        startX = Math.max(startX, ((x + width) - padding) - contentWidth);
      } else {
        startX = Math.max(startX, x + ((width - contentWidth) / 2));
      }
    }
    int headerHeight = parent.getHeaderHeight();
    gc.setClipping(x + padding, padding, width - (2 * padding), headerHeight - (2 * padding));
    if (image != null) {
      Rectangle imageBounds = image.getBounds();
      int drawHeight = Math.min(imageBounds.height, headerHeight - (2 * padding));
      gc.drawImage(
          image,
          0,
          0,
          imageBounds.width,
          imageBounds.height,
          startX,
          (headerHeight - drawHeight) / 2,
          imageBounds.width,
          drawHeight);
      startX += imageBounds.width;
      if (displayText.length() > 0) {
        startX += Tree.MARGIN_IMAGE;
      }
    }
    if (displayText.length() > 0) {
      int fontHeight = parent.fontHeight;
      gc.drawText(displayText, startX, (headerHeight - fontHeight) / 2, DRAW_MNEMONIC);
    }
  }
}
