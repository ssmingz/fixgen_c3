class PlaceHold {
  private void onMouseMove(Event event) {
    if (!showClose) {
      return;
    }
    CTabItem item = null;
    Rectangle itemRect = null;
    for (int i = 0; i < items.length; i++) {
      itemRect = items[i].getBounds();
      if (itemRect.contains(new Point(event.x, event.y))) {
        item = items[i];
        break;
      }
    }
    if (item == inactiveItem) {
      return;
    }
    inactiveCloseBar.setVisible(false);
    inactiveItem = null;
    if ((item == null) || (item == getSelection())) {
      return;
    }
    Point closeRect = inactiveCloseBar.getSize();
    int x = (((itemRect.x + itemRect.width) - CTabItem.RIGHT_MARGIN) - closeRect.x) + 2;
    int y = (itemRect.y + CTabItem.TOP_MARGIN) - 1;
    if (scrollBar.isVisible()) {
      Rectangle scrollArea = scrollBar.getBounds();
      scrollArea.width += borderRight;
      if (scrollArea.contains(x, y)) {
        return;
      }
    }
    inactiveCloseBar.setLocation(x, y);
    inactiveCloseBar.setVisible(true);
    inactiveItem = item;
  }
}
