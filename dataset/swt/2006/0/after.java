class PlaceHold {
  void drawMinimize(GC gc) {
    if ((minRect.width == 0) || (minRect.height == 0)) {
      return;
    }
    Display display = getDisplay();
    int indent = Math.max(1, (CTabFolder2.BUTTON_SIZE - 9) / 2);
    int x = (minRect.x + indent) - 1;
    int y = minRect.y + indent;
    switch (minImageState) {
      case NORMAL:
        {
          if (!minimized) {
            gc.setBackground(getDisplay().getSystemColor(COLOR_WHITE));
            gc.fillRectangle(x, y, 9, 3);
            gc.setForeground(borderColor);
            gc.drawRectangle(x, y, 9, 3);
          } else {
            gc.setBackground(getDisplay().getSystemColor(COLOR_WHITE));
            gc.fillRectangle(x, y + 3, 5, 4);
            gc.fillRectangle(x + 2, y, 5, 4);
            gc.setForeground(borderColor);
            gc.drawRectangle(x, y + 3, 5, 4);
            gc.drawRectangle(x + 2, y, 5, 4);
            gc.drawLine(x + 3, y + 1, x + 6, y + 1);
            gc.drawLine(x + 1, y + 4, x + 4, y + 4);
          }
          break;
        }
      case HOT:
        {
          Color border = new Color(display, MINMAX_BORDER);
          if (!minimized) {
            gc.setBackground(getDisplay().getSystemColor(COLOR_WHITE));
            gc.fillRectangle(x, y, 9, 3);
            gc.setForeground(border);
            gc.drawRectangle(x, y, 9, 3);
          } else {
            gc.setBackground(getDisplay().getSystemColor(COLOR_WHITE));
            gc.fillRectangle(x, y + 3, 5, 4);
            gc.fillRectangle(x + 2, y, 5, 4);
            gc.setForeground(border);
            gc.drawRectangle(x, y + 3, 5, 4);
            gc.drawRectangle(x + 2, y, 5, 4);
            gc.drawLine(x + 3, y + 1, x + 6, y + 1);
            gc.drawLine(x + 1, y + 4, x + 4, y + 4);
          }
          border.dispose();
          break;
        }
      case SELECTED:
        {
          Color fill = new Color(display, MINMAX_FILL);
          Color border = new Color(display, MINMAX_BORDER);
          if (!minimized) {
            gc.setBackground(fill);
            gc.fillRectangle(x + 1, y + 1, 9, 3);
            gc.setForeground(border);
            gc.drawRectangle(x + 1, y + 1, 9, 3);
          } else {
            gc.setBackground(fill);
            gc.fillRectangle(x + 1, y + 4, 5, 4);
            gc.fillRectangle(x + 3, y + 1, 5, 4);
            gc.setForeground(border);
            gc.drawRectangle(x + 1, y + 4, 5, 4);
            gc.drawRectangle(x + 3, y + 1, 5, 4);
            gc.drawLine(x + 4, y + 2, x + 7, y + 2);
            gc.drawLine(x + 2, y + 5, x + 5, y + 5);
          }
          fill.dispose();
          border.dispose();
          break;
        }
    }
  }
}
