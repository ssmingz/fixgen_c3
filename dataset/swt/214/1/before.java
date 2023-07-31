class PlaceHold {
  void drawChevron(GC gc) {
    if ((chevronRect.width == 0) || (chevronRect.height == 0)) {
      return;
    }
    int x = chevronRect.x;
    int y = chevronRect.y;
    int width = chevronRect.width;
    int height = chevronRect.height;
    gc.setBackground(getParent().getBackground());
    gc.fillRectangle(chevronRect);
    int indent = (tabHeight - 5) / 2;
    gc.setForeground(selectionForeground);
    gc.drawLine(x + 7, y + indent, x + 9, (y + indent) + 2);
    gc.drawLine(x + 8, (y + indent) + 3, x + 7, (y + indent) + 4);
    gc.drawLine(x + 8, (y + indent) + 2, x + 8, (y + indent) + 2);
    gc.drawLine(x + 10, y + indent, x + 12, (y + indent) + 2);
    gc.drawLine(x + 11, (y + indent) + 3, x + 10, (y + indent) + 4);
    gc.drawLine(x + 11, (y + indent) + 2, x + 11, (y + indent) + 2);
  }
}
