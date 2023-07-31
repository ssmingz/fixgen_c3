class PlaceHold {
  void drawUnselected(GC gc) {
    if (!isShowing()) {
      return;
    }
    if (((background != null) || (bgImage != null)) || (gradientColors != null)) {
      int x1 = x;
      int y1 = (parent.onBottom) ? y : y + 1;
      int x2 = x + width;
      int y2 = (parent.onBottom) ? (y + height) - 1 : y + height;
      int index = parent.indexOf(this);
      if (((!parent.simple) && (!parent.single)) && (parent.selectedIndex != (-1))) {
        if ((parent.selectedIndex + 1) == index) {
          x1 -= CTabFolder.CURVE_WIDTH / 2;
        }
        if ((parent.selectedIndex - 1) == index) {
          x2 += CTabFolder.CURVE_WIDTH / 2;
        }
      }
      int[] shape = null;
      if (index == parent.firstIndex) {
        if (parent.borderLeft != 0) {
          x1 += 1;
        }
        int[] left = (parent.onBottom) ? CTabFolder.BOTTOM_LEFT_CORNER : CTabFolder.TOP_LEFT_CORNER;
        shape = new int[left.length + 6];
        int i = 0;
        shape[i++] = x1;
        shape[i++] = (parent.onBottom) ? y1 : y2;
        for (int j = 0; j < (left.length / 2); j++) {
          shape[i++] = x1 + left[2 * j];
          shape[i++] = (parent.onBottom) ? y2 + left[(2 * j) + 1] : (y1 + left[(2 * j) + 1]) - 1;
          if (parent.borderLeft == 0) {
            shape[i - 1] += (parent.onBottom) ? 1 : -1;
          }
        }
        shape[i++] = x2;
        shape[i++] = (parent.onBottom) ? y2 : y1;
        shape[i++] = x2;
        shape[i++] = (parent.onBottom) ? y1 : y2;
      } else {
        shape = new int[] {x1, y1, x2, y1, x2, y2, x1, y2};
      }
      Color defaultBackground = (background != null) ? background : parent.getBackground();
      parent.drawBackground(
          gc,
          shape,
          defaultBackground,
          bgImage,
          gradientColors,
          gradientPercents,
          gradientVertical);
    }
    if (parent.indexOf(this) != (parent.selectedIndex - 1)) {
      gc.setForeground(borderColor);
      gc.drawLine((x + width) - 1, y, (x + width) - 1, y + height);
    }
    int xDraw = x + marginLeft();
    Image image = getImage();
    if ((image != null) && parent.showUnselectedImage) {
      Rectangle imageBounds = image.getBounds();
      int imageX = xDraw;
      int imageHeight = imageBounds.height;
      int imageY = y + ((height - imageHeight) / 2);
      imageY += (parent.onBottom) ? -1 : 1;
      int imageWidth = (imageBounds.width * imageHeight) / imageBounds.height;
      gc.drawImage(
          image,
          imageBounds.x,
          imageBounds.y,
          imageBounds.width,
          imageBounds.height,
          imageX,
          imageY,
          imageWidth,
          imageHeight);
      xDraw += imageWidth + INTERNAL_SPACING;
    }
    int textWidth = ((x + width) - xDraw) - marginRight();
    if (closeRect.width > 0) {
      textWidth -= closeRect.width + INTERNAL_SPACING;
    }
    Font gcFont = gc.getFont();
    if (font != null) {
      gc.setFont(font);
    }
    if ((shortenedText == null) || (shortenedTextWidth != textWidth)) {
      shortenedText = shortenText(gc, getText(), textWidth);
      shortenedTextWidth = textWidth;
    }
    Point extent = gc.textExtent(shortenedText, FLAGS);
    int textY = y + ((height - extent.y) / 2);
    textY += (parent.onBottom) ? -1 : 1;
    gc.setForeground(foreground != null ? foreground : parent.getForeground());
    gc.drawText(shortenedText, xDraw, textY, FLAGS);
    gc.setFont(gcFont);
    if (parent.showUnselectedClose && (parent.showClose || showClose)) {
      drawClose(gc);
    }
  }
}
