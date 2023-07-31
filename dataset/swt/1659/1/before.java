class PlaceHold {
  void drawHighlightShadow(GC gc, int itemIndex) {
    Rectangle bounds = getBounds(itemIndex);
    Color oldForeground = getForeground();
    gc.setForeground(getDisplay().getSystemColor(COLOR_WIDGET_HIGHLIGHT_SHADOW));
    gc.drawLine(bounds.x, bounds.y, (bounds.x + bounds.width) - 1, bounds.y);
    gc.drawLine(bounds.x, bounds.y, bounds.x, (bounds.y + bounds.height) - 1);
    gc.setForeground(oldForeground);
  }
}
