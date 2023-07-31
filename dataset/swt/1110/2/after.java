class PlaceHold {
  int getLastIndex() {
    if (single) {
      return selectedIndex;
    }
    if (items.length == 0) {
      return -1;
    }
    int edge = getRightItemEdge();
    for (int i = firstIndex; i < items.length; i++) {
      CTabItem item = items[i];
      if ((item.x + item.width) > edge) {
        return i == firstIndex ? firstIndex : i - 1;
      }
    }
    return items.length - 1;
  }
}
