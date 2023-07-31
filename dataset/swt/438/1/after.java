class PlaceHold {
  void drawBody(Event event) {
    GC gc = event.gc;
    Point size = getSize();
    if (!minimized) {
      int width = ((size.x - borderLeft) - borderRight) - (2 * highlight_margin);
      int height =
          ((((size.y - borderTop) - borderBottom) - tabHeight) - highlight_header)
              - highlight_margin;
      if (highlight_margin > 0) {
        int[] shape = null;
        if (onBottom) {
          int x1 = borderLeft;
          int y1 = borderTop;
          int x2 = size.x - borderRight;
          int y2 = ((size.y - borderBottom) - tabHeight) - highlight_header;
          shape =
              new int[] {
                x1,
                y1,
                x2,
                y1,
                x2,
                y2,
                x2 - highlight_margin,
                y2,
                x2 - highlight_margin,
                y1 + highlight_margin,
                x1 + highlight_margin,
                y1 + highlight_margin,
                x1 + highlight_margin,
                y2,
                x1,
                y2
              };
        } else {
          int x1 = borderLeft;
          int y1 = (borderTop + tabHeight) + highlight_header;
          int x2 = size.x - borderRight;
          int y2 = size.y - borderBottom;
          shape =
              new int[] {
                x1,
                y1,
                x1 + highlight_margin,
                y1,
                x1 + highlight_margin,
                y2 - highlight_margin,
                x2 - highlight_margin,
                y2 - highlight_margin,
                x2 - highlight_margin,
                y1,
                x2,
                y1,
                x2,
                y2,
                x1,
                y2
              };
        }
        if ((((selectedIndex != (-1)) && (selectionGradientColors != null))
                && (selectionGradientColors.length > 1))
            && (!selectionGradientVertical)) {
          drawBackground(gc, shape, true);
        } else if ((((selectedIndex == (-1)) && (gradientColors != null))
                && (gradientColors.length > 1))
            && (!gradientVertical)) {
          drawBackground(gc, shape, false);
        } else {
          gc.setBackground(selectedIndex == (-1) ? getBackground() : selectionBackground);
          gc.fillPolygon(shape);
        }
      }
      if ((getStyle() & SWT.NO_BACKGROUND) != 0) {
        gc.setBackground(getBackground());
        gc.fillRectangle(xClient - marginWidth, yClient - marginHeight, width, height);
      }
    } else if ((getStyle() & SWT.NO_BACKGROUND) != 0) {
      int height = ((borderTop + tabHeight) + highlight_header) + borderBottom;
      if (size.y > height) {
        gc.setBackground(getParent().getBackground());
        gc.fillRectangle(0, height, size.x, size.y - height);
      }
    }
    if (borderLeft > 0) {
      gc.setForeground(getDisplay().getSystemColor(BORDER1_COLOR));
      int x1 = borderLeft - 1;
      int x2 = size.x - borderRight;
      int y1 = (onBottom) ? borderTop - 1 : borderTop + tabHeight;
      int y2 = (onBottom) ? ((size.y - tabHeight) - borderBottom) - 1 : size.y - borderBottom;
      gc.drawLine(x1, y1, x1, y2);
      gc.drawLine(x2, y1, x2, y2);
      if (onBottom) {
        gc.drawLine(x1, y1, x2, y1);
      } else {
        gc.drawLine(x1, y2, x2, y2);
      }
    }
  }
}
