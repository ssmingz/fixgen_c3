class PlaceHold {
  void onPaint(Event event) {
    Font font = getFont();
    if ((oldFont == null) || (!oldFont.equals(font))) {
      oldFont = font;
      if (!updateTabHeight(tabHeight)) {
        updateItems();
        redraw();
      }
    }
    GC gc = event.gc;
    Point size = getSize();
    Color parentBackground = getParent().getBackground();
    Color background = getBackground();
    if (items.length == 0) {
      gc.setBackground(parentBackground);
      gc.fillRectangle(0, 0, size.x, size.y);
      return;
    }
    if (single) {
      if (selectedIndex != (-1)) {
        CTabItem2 item = items[selectedIndex];
        int[] shapeLeft = null;
        int[] shapeRight = null;
        if (onBottom) {
          int x = Math.max(0, borderLeft - 1);
          if ((borderLeft > 1) & showHighlight) {
            x -= HIGHLIGHT_MARGIN;
          }
          int y = (size.y - borderBottom) - tabHeight;
          int width = item.x - x;
          int height = tabHeight;
          if (borderRight > 0) {
            height -= 1;
          }
          shapeLeft = new int[CTabFolder2.BOTTOM_LEFT_OUTSIDE_CORNER.length + 6];
          int index = 0;
          shapeLeft[index++] = x;
          shapeLeft[index++] = y;
          for (int i = 0; i < (CTabFolder2.BOTTOM_LEFT_OUTSIDE_CORNER.length / 2); i++) {
            shapeLeft[index++] = x + BOTTOM_LEFT_OUTSIDE_CORNER[2 * i];
            shapeLeft[index++] = (y + height) + BOTTOM_LEFT_OUTSIDE_CORNER[(2 * i) + 1];
          }
          shapeLeft[index++] = x + width;
          shapeLeft[index++] = y + height;
          shapeLeft[index++] = x + width;
          shapeLeft[index++] = y;
          x = item.x + item.width;
          width = (size.x - borderRight) - x;
          if ((borderRight > 1) & showHighlight) {
            width += HIGHLIGHT_MARGIN;
          }
          if (borderRight > 0) {
            width += 1;
          }
          shapeRight = new int[CTabFolder2.BOTTOM_RIGHT_OUTSIDE_CORNER.length + 6];
          index = 0;
          shapeRight[index++] = x;
          shapeRight[index++] = y;
          shapeRight[index++] = x;
          shapeRight[index++] = y + height;
          for (int i = 0; i < (CTabFolder2.BOTTOM_RIGHT_OUTSIDE_CORNER.length / 2); i++) {
            shapeRight[index++] = (x + width) + BOTTOM_RIGHT_OUTSIDE_CORNER[2 * i];
            shapeRight[index++] = (y + height) + BOTTOM_RIGHT_OUTSIDE_CORNER[(2 * i) + 1];
          }
          shapeRight[index++] = x + width;
          shapeRight[index++] = y;
        } else {
          int x = Math.max(0, borderLeft - 1);
          if ((borderLeft > 1) & showHighlight) {
            x -= HIGHLIGHT_MARGIN;
          }
          int y = borderTop;
          int width = item.x - x;
          int height = tabHeight;
          shapeLeft = new int[CTabFolder2.TOP_LEFT_OUTSIDE_CORNER.length + 6];
          int index = 0;
          shapeLeft[index++] = x;
          shapeLeft[index++] = y + height;
          for (int i = 0; i < (CTabFolder2.TOP_LEFT_OUTSIDE_CORNER.length / 2); i++) {
            shapeLeft[index++] = x + TOP_LEFT_OUTSIDE_CORNER[2 * i];
            shapeLeft[index++] = y + TOP_LEFT_OUTSIDE_CORNER[(2 * i) + 1];
          }
          shapeLeft[index++] = x + width;
          shapeLeft[index++] = y;
          shapeLeft[index++] = x + width;
          shapeLeft[index++] = y + height;
          x = item.x + item.width;
          width = (size.x - borderRight) - x;
          if ((borderRight > 1) & showHighlight) {
            width += HIGHLIGHT_MARGIN;
          }
          if (borderRight > 0) {
            width += 1;
          }
          shapeRight = new int[CTabFolder2.TOP_RIGHT_OUTSIDE_CORNER.length + 6];
          index = 0;
          shapeRight[index++] = x;
          shapeRight[index++] = y + height;
          shapeRight[index++] = x;
          shapeRight[index++] = y;
          for (int i = 0; i < (CTabFolder2.TOP_RIGHT_OUTSIDE_CORNER.length / 2); i++) {
            shapeRight[index++] = (x + width) + TOP_RIGHT_OUTSIDE_CORNER[2 * i];
            shapeRight[index++] = y + TOP_RIGHT_OUTSIDE_CORNER[(2 * i) + 1];
          }
          shapeRight[index++] = x + width;
          shapeRight[index++] = y + height;
        }
        Region r = new Region();
        int x = Math.max(0, borderLeft - 1);
        if ((borderLeft > 1) & showHighlight) {
          x -= HIGHLIGHT_MARGIN;
        }
        int y = (onBottom) ? (size.y - borderBottom) - tabHeight : borderTop;
        int width = item.x - x;
        int height = tabHeight;
        r.add(new Rectangle(x, y, width, height));
        r.subtract(shapeLeft);
        gc.setBackground(getParent().getBackground());
        fillRegion(gc, r);
        gc.setBackground(background);
        gc.fillPolygon(shapeLeft);
        x = item.x + item.width;
        width = (size.x - borderRight) - x;
        if ((borderRight > 1) & showHighlight) {
          width += borderRight;
        }
        if (width > 0) {
          if (borderRight > 0) {
            width += 1;
          }
          r.subtract(r);
          r.add(new Rectangle(x, y, width, height));
          r.subtract(shapeRight);
          gc.setBackground(getParent().getBackground());
          fillRegion(gc, r);
          gc.setBackground(background);
          gc.fillPolygon(shapeRight);
        }
        r.dispose();
      }
    } else {
      CTabItem2 lastItem = items[items.length - 1];
      int edge = lastItem.x + lastItem.width;
      if (edge < size.x) {
        int x = edge;
        int y = (onBottom) ? ((size.y - borderBottom) - tabHeight) - 1 : borderTop;
        int width = ((size.x - edge) - borderRight) + 1;
        int height = tabHeight + 1;
        gc.setBackground(parentBackground);
        gc.fillRectangle(x, y, width, height);
      }
    }
    if (!single) {
      for (int i = 0; i < items.length; i++) {
        if ((i != selectedIndex) && event.getBounds().intersects(items[i].getBounds())) {
          items[i].onPaint(gc, false);
        }
      }
    }
    if (selectedIndex != (-1)) {
      CTabItem2 item = items[selectedIndex];
      Rectangle rect = item.getBounds();
      if (event.getBounds().intersects(rect.x, rect.y, rect.width + CURVE_WIDTH, rect.height)) {
        item.onPaint(gc, true);
      }
    }
    drawClose(gc);
    drawChevron(gc);
    drawExpand(gc);
    drawBorder(gc);
    int width = (size.x - borderLeft) - borderRight;
    int height = (((size.y - borderTop) - borderBottom) - tabHeight) - HIGHLIGHT_HEADER;
    int x = xClient - marginWidth;
    int y = yClient - marginHeight;
    gc.setForeground(background);
    gc.fillRectangle(x, y, width, height);
    gc.setForeground(getForeground());
    gc.setBackground(getBackground());
  }
}
