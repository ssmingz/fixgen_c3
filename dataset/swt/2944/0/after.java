class PlaceHold {
  void setLastItem(int index) {
    if ((index < 0) || (index > (items.length - 1))) {
      return;
    }
    Point size = getSize();
    if (size.x <= 0) {
      return;
    }
    int maxWidth =
        ((((size.x - borderLeft) - borderRight) - minRect.width) - maxRect.width)
            - chevronRect.width;
    int tabWidth = items[index].width;
    while (index > 0) {
      tabWidth += items[index - 1].width;
      if (tabWidth > maxWidth) {
        break;
      }
      index--;
    }
    if (topTabIndex == index) {
      return;
    }
    topTabIndex = index;
    setItemLocation();
    redraw();
  }
}
