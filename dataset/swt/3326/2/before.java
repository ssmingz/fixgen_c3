class PlaceHold {
  int hit(Theme theme, Point position, Rectangle bounds) {
    if (!bounds.contains(position)) {
      return DrawData.WIDGET_NOWHERE;
    }
    int treeHandle = theme.treeHandle;
    int expander_size = theme.getWidgetProperty(treeHandle, "expander-size");
    if (new Rectangle(bounds.x, bounds.y, expander_size, expander_size).contains(position)) {
      return DrawData.WIDGET_WHOLE;
    }
    return DrawData.WIDGET_NOWHERE;
  }
}
