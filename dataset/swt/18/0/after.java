class PlaceHold {
  void drawUnselected(GC gc) {
    int rightTabEdge = parent.getRightItemEdge();
    if ((parent.items[parent.topTabIndex] != this) && ((x + width) > rightTabEdge)) {
      return;
    }
    if (parent.indexOf(this) != (parent.selectedIndex - 1)) {
      gc.setForeground(borderColor);
      gc.drawLine((x + width) - 1, y, (x + width) - 1, y + height);
    }
    int textWidth = (width - LEFT_MARGIN) - RIGHT_MARGIN;
    if (closeRect.width > 0) {
      textWidth -= closeRect.width + INTERNAL_SPACING;
    }
    if ((shortenedText == null) || (shortenedTextWidth != textWidth)) {
      shortenedText = shortenText(gc, getText(), textWidth);
      shortenedTextWidth = textWidth;
    }
    Point extent = gc.textExtent(shortenedText, FLAGS);
    int textY = y + ((height - extent.y) / 2);
    textY += (parent.onBottom) ? -1 : 1;
    gc.setForeground(parent.getForeground());
    gc.drawText(shortenedText, x + LEFT_MARGIN, textY, FLAGS);
    if (parent.showClose || showClose) {
      drawClose(gc);
    }
  }
}
