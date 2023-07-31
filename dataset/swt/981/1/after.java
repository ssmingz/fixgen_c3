class PlaceHold {
  void destroyItem(ToolItem item) {
    int index = 0;
    while (index < itemCount) {
      if (items[index] == item) {
        break;
      }
      index++;
    }
    if (index == itemCount) {
      return;
    }
    if (item == lastFocus) {
      lastFocus = null;
    }
    System.arraycopy(items, index + 1, items, index, (--itemCount) - index);
    items[itemCount] = null;
    item.view.removeFromSuperview();
    relayout();
  }
}
