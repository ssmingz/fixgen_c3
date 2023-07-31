class PlaceHold {
  void drawChevron(GC gc) {
    if ((chevronRect.width == 0) || (chevronRect.height == 0)) {
      return;
    }
    Display display = getDisplay();
    int indent = Math.max(1, (CTabFolder.BUTTON_SIZE - 9) / 2);
    int x = chevronRect.x + indent;
    int y = chevronRect.y + indent;
    switch (chevronImageState) {
      case NORMAL:
        {
          int[] shape =
              (onBottom)
                  ? new int[] {
                    x, y + 9, x + 9, y + 9, x + 9, y + 7, x + 5, y + 3, x + 4, y + 3, x, y + 7, x,
                    y + 9
                  }
                  : new int[] {
                    x, y, x + 9, y, x + 9, y + 2, x + 5, y + 6, x + 4, y + 6, x, y + 2, x, y
                  };
          gc.setBackground(display.getSystemColor(COLOR_WHITE));
          gc.fillPolygon(shape);
          gc.setForeground(borderColor);
          gc.drawPolygon(shape);
          break;
        }
      case HOT:
        {
          int[] shape =
              (onBottom)
                  ? new int[] {
                    x, y + 9, x + 9, y + 9, x + 9, y + 7, x + 5, y + 3, x + 4, y + 3, x, y + 7, x,
                    y + 9
                  }
                  : new int[] {
                    x, y, x + 9, y, x + 9, y + 2, x + 5, y + 6, x + 4, y + 6, x, y + 2, x, y
                  };
          gc.setBackground(display.getSystemColor(COLOR_WHITE));
          gc.fillPolygon(shape);
          Color border = new Color(display, CHEVRON_BORDER);
          gc.setForeground(border);
          gc.drawPolygon(shape);
          border.dispose();
          break;
        }
      case SELECTED:
        {
          int[] shape =
              (onBottom)
                  ? new int[] {
                    x + 1, y + 10, x + 10, y + 10, x + 10, y + 8, x + 6, y + 4, x + 5, y + 4, x + 1,
                    y + 8, x + 1, y + 10
                  }
                  : new int[] {
                    x + 1, y + 1, x + 10, y + 1, x + 10, y + 3, x + 6, y + 7, x + 5, y + 7, x + 1,
                    y + 3, x + 1, y + 1
                  };
          Color fill = new Color(display, CHEVRON_FILL);
          gc.setBackground(fill);
          gc.fillPolygon(shape);
          fill.dispose();
          Color border = new Color(display, CHEVRON_BORDER);
          gc.setForeground(border);
          gc.drawPolygon(shape);
          border.dispose();
          break;
        }
    }
  }
}
