class PlaceHold {
  void drawUnselected(GC gc) {
    int rightTabEdge = parent.getRightItemEdge();
    if (x >= parent.getSize().x) {
      return;
    }
    if ((parent.items[parent.topTabIndex] != this) && ((x + width) >= rightTabEdge)) {
      int x1 = x;
      int y1 = y - 1;
      int x2 = parent.getSize().x;
      int y2 = y + height;
      int[] shape = new int[] {x1, y1, x1, y2, x2, y2, x2, y1};
      parent.drawBackground(gc, shape, false);
      return;
    }
    int[] shape = null;
    if (parent.indexOf(this) == parent.topTabIndex) {
      if (this.parent.onBottom) {
        int[] left = CTabFolder2.BOTTOM_LEFT_CORNER;
        shape = new int[left.length + 6];
        int index = 0;
        shape[index++] = x;
        shape[index++] = y;
        for (int i = 0; i < (left.length / 2); i++) {
          shape[index++] = x + left[2 * i];
          shape[index++] = (y + height) + left[(2 * i) + 1];
        }
        shape[index++] = x + width;
        shape[index++] = y + height;
        shape[index++] = x + width;
        shape[index++] = y;
      } else {
        int[] left = CTabFolder2.TOP_LEFT_CORNER;
        shape = new int[left.length + 6];
        int index = 0;
        shape[index++] = x;
        shape[index++] = y + height;
        for (int i = 0; i < (left.length / 2); i++) {
          shape[index++] = x + left[2 * i];
          shape[index++] = y + left[(2 * i) + 1];
        }
        shape[index++] = x + width;
        shape[index++] = y;
        shape[index++] = x + width;
        shape[index++] = y + height;
      }
      parent.drawBackground(gc, shape, false);
      Region r = new Region();
      r.add(new Rectangle(x, y, width, height));
      r.subtract(shape);
      gc.setBackground(parent.getParent().getBackground());
      CTabFolder2.fillRegion(gc, r);
      r.dispose();
    } else {
      shape = new int[8];
      shape[0] = x;
      shape[1] = y;
      shape[2] = x;
      shape[3] = y + height;
      shape[4] = x + width;
      shape[5] = y + height;
      shape[6] = x + width;
      shape[7] = y;
      parent.drawBackground(gc, shape, false);
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
