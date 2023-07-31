class PlaceHold {
  void setLastIndex(int index) {
    if ((index < 0) || (index > (items.length - 1))) {
      return;
    }
    Point size = getSize();
    if (size.x <= 0) {
      return;
    }
    int maxWidth = getRightItemEdge() - borderLeft;
    int tabWidth = items[index].width;
    while (index > 0) {
      tabWidth += items[index - 1].width;
      if (tabWidth >= maxWidth) {
        break;
      }
      index--;
    }
    if (firstIndex == index) {
      return;
    }
    firstIndex = index;
    setItemLocation();
    setButtonBounds();
    redraw();
  }
}
