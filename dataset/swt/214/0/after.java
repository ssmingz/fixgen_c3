class PlaceHold {
  void drawClose(GC gc) {
    if ((closeRect.width == 0) || (closeRect.height == 0)) {
      return;
    }
    int x = closeRect.x;
    int y = closeRect.y;
    int width = closeRect.width;
    int height = closeRect.height;
    if (!single) {
      gc.setBackground(getParent().getBackground());
      gc.fillRectangle(closeRect);
    }
    int indent = (tabHeight - 5) / 2;
    Color color = (single) ? selectionForeground : getParent().getForeground();
    gc.setForeground(color);
    gc.drawLine(x + 6, y + indent, x + 10, (y + indent) + 5);
    gc.drawLine(x + 7, y + indent, x + 11, (y + indent) + 5);
    gc.drawLine(x + 6, (y + indent) + 5, x + 10, y + indent);
    gc.drawLine(x + 7, (y + indent) + 5, x + 11, y + indent);
  }
}
