class PlaceHold {
  void drawMaximize(GC gc) {
    if ((maxRect.width == 0) || (maxRect.height == 0)) {
      return;
    }
    Display display = getDisplay();
    Color maxBorder = (single) ? getSelectionForeground() : getForeground();
    int indent = Math.max(1, (CTabFolder.BUTTON_SIZE - 9) / 2);
    int x = (maxRect.x + indent) - 1;
    int y = maxRect.y + indent;
    switch (maxImageState) {
      case NORMAL:
        {
          if (!maximized) {
            gc.setBackground(getDisplay().getSystemColor(COLOR_WHITE));
            gc.fillRectangle(x, y, 7, 9);
            gc.setForeground(maxBorder);
            gc.drawRectangle(x, y, 7, 9);
            gc.drawLine(x + 1, y + 2, x + 6, y + 2);
          } else {
            gc.setBackground(getDisplay().getSystemColor(COLOR_WHITE));
            gc.fillRectangle(x, y + 3, 5, 4);
            gc.fillRectangle(x + 2, y, 5, 4);
            gc.setForeground(maxBorder);
            gc.drawRectangle(x, y + 3, 5, 4);
            gc.drawRectangle(x + 2, y, 5, 4);
            gc.drawLine(x + 3, y + 1, x + 6, y + 1);
            gc.drawLine(x + 1, y + 4, x + 4, y + 4);
          }
          break;
        }
      case HOT:
        {
          Color fill = new Color(display, MINMAX_FILL);
          if (!maximized) {
            gc.setBackground(fill);
            gc.fillRectangle(x, y, 7, 9);
            gc.setForeground(maxBorder);
            gc.drawRectangle(x, y, 7, 9);
            gc.drawLine(x + 1, y + 2, x + 6, y + 2);
          } else {
            gc.setBackground(fill);
            gc.fillRectangle(x, y + 3, 5, 4);
            gc.fillRectangle(x + 2, y, 5, 4);
            gc.setForeground(maxBorder);
            gc.drawRectangle(x, y + 3, 5, 4);
            gc.drawRectangle(x + 2, y, 5, 4);
            gc.drawLine(x + 3, y + 1, x + 6, y + 1);
            gc.drawLine(x + 1, y + 4, x + 4, y + 4);
          }
          fill.dispose();
          break;
        }
      case SELECTED:
        {
          Color fill = new Color(display, MINMAX_FILL);
          if (!maximized) {
            gc.setBackground(fill);
            gc.fillRectangle(x + 1, y + 1, 7, 9);
            gc.setForeground(maxBorder);
            gc.drawRectangle(x + 1, y + 1, 7, 9);
            gc.drawLine(x + 2, y + 3, x + 7, y + 3);
          } else {
            gc.setBackground(fill);
            gc.fillRectangle(x + 1, y + 4, 5, 4);
            gc.fillRectangle(x + 3, y + 1, 5, 4);
            gc.setForeground(maxBorder);
            gc.drawRectangle(x + 1, y + 4, 5, 4);
            gc.drawRectangle(x + 3, y + 1, 5, 4);
            gc.drawLine(x + 4, y + 2, x + 7, y + 2);
            gc.drawLine(x + 2, y + 5, x + 5, y + 5);
          }
          fill.dispose();
          break;
        }
    }
  }
}
