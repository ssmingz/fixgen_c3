class PlaceHold {
  void drawExpand(GC gc) {
    if ((expandRect.width == 0) || (expandRect.height == 0)) {
      return;
    }
    int x = expandRect.x;
    int y = expandRect.y;
    int width = expandRect.width;
    int height = expandRect.height;
    if (!single) {
      gc.setBackground(getParent().getBackground());
      gc.fillRectangle(expandRect);
    }
    int indent = (tabHeight - 7) / 2;
    int[] points = null;
    if (expanded && onBottom) {
      points =
          new int[] {
            x + 3,
            (((y + height) - tabHeight) + indent) + 6,
            x + 12,
            (((y + height) - tabHeight) + indent) + 6,
            x + 7,
            (((y + height) - tabHeight) + indent) + 1
          };
    }
    if (expanded && (!onBottom)) {
      points =
          new int[] {x + 4, (y + indent) + 2, x + 11, (y + indent) + 2, x + 7, (y + indent) + 6};
    }
    if ((!expanded) && onBottom) {
      points =
          new int[] {
            x + 7,
            (((y + height) - tabHeight) + indent) + 1,
            x + 11,
            (((y + height) - tabHeight) + indent) + 5,
            x + 7,
            (((y + height) - tabHeight) + indent) + 9
          };
    }
    if ((!expanded) && (!onBottom)) {
      points =
          new int[] {x + 7, (y + indent) - 2, x + 11, (y + indent) + 2, x + 7, (y + indent) + 6};
    }
    Color color = (single) ? selectionForeground : getParent().getForeground();
    gc.setBackground(color);
    gc.fillPolygon(points);
  }
}
